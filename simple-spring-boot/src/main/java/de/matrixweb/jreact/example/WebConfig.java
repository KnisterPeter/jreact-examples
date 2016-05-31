package de.matrixweb.jreact.example;

import javax.servlet.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

/**
 * @author markusw
 */
@Configuration
public class WebConfig {

  /**
   * @return Returns the rendering filter
   */
  @Bean
  public Filter jreactFilter() {
    return new JReactFilter();
  }

}
