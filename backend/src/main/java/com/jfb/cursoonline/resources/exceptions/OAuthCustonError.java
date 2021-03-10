package com.jfb.cursoonline.resources.exceptions;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthCustonError implements Serializable {
  private static final long serialVersionUID = 1L;

  private String error;

  @JsonProperty("Error_description")
  private String errorDescription;

  public OAuthCustonError() {
  }

  public OAuthCustonError(String error, String errorDescription) {
    this.error = error;
    this.errorDescription = errorDescription;
  }

  public String getError() {
    return this.error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getErrorDescription() {
    return this.errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

}
