package com.jfb.cursoonline.dto;

import java.io.Serializable;

import com.jfb.cursoonline.entities.enums.DeliverStatus;

public class DeliverRevisionDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private DeliverStatus status;
  private String feedback;
  private Integer correctCount;

  public DeliverRevisionDTO() {
  }

  public DeliverRevisionDTO(DeliverStatus status, String feedback, Integer correctCount) {
    this.status = status;
    this.feedback = feedback;
    this.correctCount = correctCount;
  }

  public DeliverStatus getStatus() {
    return this.status;
  }

  public void setStatus(DeliverStatus status) {
    this.status = status;
  }

  public String getFeedback() {
    return this.feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  public Integer getCorrectCount() {
    return this.correctCount;
  }

  public void setCorrectCount(Integer correctCount) {
    this.correctCount = correctCount;
  }

}
