package de.matrixweb.jreact.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.matrixweb.jreact.JReact;

/**
 * @author markusw
 */
public class JReactFilter implements Filter {

  public void init(FilterConfig filterConfig) throws ServletException {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String accept = httpRequest.getHeader("Accept");
    if (!accept.contains("text/html")) {
      chain.doFilter(request, response);
      return;
    }

    CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) response);
    chain.doFilter(new RequestWrapper((HttpServletRequest) request), wrapper);

    // JavaScript is single-threaded
    // either recreate like here (bad for performance but easy) or use an object
    // pool
    JReact react = new JReact(true);
    react.addRequirePath(".");

    Map<String, Object> appProps = wrapper.toJson();
    String app = react.renderToString("./public/app.js", appProps);

    Map<String, Object> frameProps = new HashMap<>();
    frameProps.put("body", app);
    react.reset();
    String html = react.renderToStaticMarkup("./public/frame.js", frameProps);

    response.setContentType("text/html");
    response.getWriter().print(html);
  }

  public void destroy() {
  }

}

class RequestWrapper extends HttpServletRequestWrapper {

  /**
   * @param request 
   */
  public RequestWrapper(HttpServletRequest request) {
    super(request);
  }

  /**
   * @see javax.servlet.http.HttpServletRequestWrapper#getHeader(java.lang.String)
   */
  @Override
  public String getHeader(String name) {
    if (name.equals("Accept")) {
      return "application/json";
    }
    return super.getHeader(name);
  }

}

class CharResponseWrapper extends HttpServletResponseWrapper {

  private StringWriter writer;

  private ByteArrayOutputStream baos;

  public CharResponseWrapper(HttpServletResponse response) {
    super(response);
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    if (writer != null) {
      return writer.toString();
    } else if (baos != null) {
      try {
        return baos.toString("UTF-8");
      } catch (UnsupportedEncodingException e) {
        // May not happen
        return null;
      }
    }
    throw new IllegalStateException("No response");
  }

  @SuppressWarnings("unchecked")
  public Map<String, Object> toJson() throws IOException {
    return new ObjectMapper().readValue(this.toString(), Map.class);
  }

  /**
   * @see javax.servlet.ServletResponseWrapper#getWriter()
   */
  @Override
  public PrintWriter getWriter() throws IOException {
    if (writer == null) {
      writer = new StringWriter();
    }
    return new PrintWriter(writer);
  }

  /**
   * @see javax.servlet.ServletResponseWrapper#getOutputStream()
   */
  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    if (baos == null) {
      baos = new ByteArrayOutputStream();
    }
    return new ServletOutputStream() {

      @Override
      public void write(int b) throws IOException {
        baos.write(b);
      }

      /**
       * @see java.io.OutputStream#write(byte[])
       */
      @Override
      public void write(byte[] b) throws IOException {
        baos.write(b);
      }

      /**
       * @see java.io.OutputStream#write(byte[], int, int)
       */
      @Override
      public void write(byte[] b, int off, int len) throws IOException {
        baos.write(b, off, len);
      }

      @Override
      public void setWriteListener(WriteListener listener) {
      }

      @Override
      public boolean isReady() {
        return true;
      }
    };
  }

}
