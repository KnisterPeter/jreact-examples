package de.matrixweb.jreact.example;

/**
 * @author markusw
 */
public class DataModel {

  private String message = "value";

  /**
   * 
   */
  public DataModel() {
  }

  /**
   * @param message 
   */
  public DataModel(String message) {
    this.message = message;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return this.message;
  }

  /**
   * @param message
   *          the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

}
