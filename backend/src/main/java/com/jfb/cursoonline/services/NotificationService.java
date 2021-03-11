package com.jfb.cursoonline.services;

import com.jfb.cursoonline.dto.NotificationDTO;
import com.jfb.cursoonline.entities.Notification;
import com.jfb.cursoonline.entities.User;
import com.jfb.cursoonline.repositories.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
  
  @Autowired
  private NotificationRepository repository;

  @Autowired
  private AuthService authService;

  public Page<NotificationDTO> notificationForCurrentUser(boolean unreadOnly, Pageable pageable) {
    User user = authService.authenticated();
    Page<Notification> page = repository.find(user, unreadOnly, pageable);
    return page.map(x -> new NotificationDTO(x));
  }

}
