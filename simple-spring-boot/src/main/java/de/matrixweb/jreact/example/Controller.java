package de.matrixweb.jreact.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author markusw
 */
@RestController
public class Controller {

  /**
   * @param name
   * @return Returns the data-model to render/return
   */
  @RequestMapping("/")
  public DataModel index(@RequestParam(name = "name", required = false, defaultValue = "World") String name) {
    return new DataModel(name);
  }

}
