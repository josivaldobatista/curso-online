package com.jfb.cursoonline.dto;

import java.io.Serializable;
import java.time.Instant;

import com.jfb.cursoonline.entities.Notification;

public class NotificationDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String text;
  private Instant moment;
  private boolean read;
  private String route;
  private Long userId;

  public NotificationDTO() {
  }

  public NotificationDTO(Long id, String text, Instant moment, boolean read, String route, 
    Long userId) {
    this.id = id;
    this.text = text;
    this.moment = moment;
    this.read = read;
    this.route = route;
    this.userId = userId;
  }

  public NotificationDTO(Notification entity) {
    id = entity.getId();
    text = entity.getText();
    moment = entity.getMoment();
    read = entity.isRead();
    route = entity.getRoute();
    userId = entity.getUser().getId();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Instant getMoment() {
    return this.moment;
  }

  public void setMoment(Instant moment) {
    this.moment = moment;
  }

  public boolean isRead() {
    return this.read;
  }

  public boolean getRead() {
    return this.read;
  }

  public void setRead(boolean read) {
    this.read = read;
  }

  public String getRoute() {
    return this.route;
  }

  public void setRoute(String route) {
    this.route = route;
  }

  public Long getUserId() {
    return this.userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

}
