package com.jfb.cursoonline.services;

import java.time.Instant;

import javax.annotation.PostConstruct;

import com.jfb.cursoonline.dto.NotificationDTO;
import com.jfb.cursoonline.entities.Deliver;
import com.jfb.cursoonline.entities.Notification;
import com.jfb.cursoonline.entities.User;
import com.jfb.cursoonline.observers.DeliverRevisionObserver;
import com.jfb.cursoonline.repositories.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService implements DeliverRevisionObserver {
  
  @Autowired
  private NotificationRepository repository;

  @Autowired
  private AuthService authService;

  @Autowired
  private DeliverService deliverService;

  @PostConstruct // <- Quando o NotificationService for instanciado o initialize é executado.
  private void initialize() {
    deliverService.subscribeDeliverRevisionObserver(this);
  }

  @Transactional(readOnly = true)
  public Page<NotificationDTO> notificationForCurrentUser(boolean unreadOnly, Pageable pageable) {
    User user = authService.authenticated();
    Page<Notification> page = repository.find(user, unreadOnly, pageable);
    return page.map(x -> new NotificationDTO(x));
  }

  @Transactional
  public void saveDeliverNotification(Deliver deliver) {
    Long secttionId = deliver.getLesson().getSection().getId();
    Long resourceId = deliver.getLesson().getSection().getResource().getId();
    Long offerId = deliver.getLesson().getSection().getResource().getOffer().getId();

    String route = "/offers/" + offerId + "/resources/" + resourceId + "/sections/" + secttionId;
    String text = deliver.getFeedback();
    Instant moment = Instant.now();
    User user = deliver.getEnrollment().getStudent();

    Notification notification = new Notification(null, text, moment, false, route, user);

    repository.save(notification);
  }

  @Override
  public void onSaveRevision(Deliver deliver) {
    saveDeliverNotification(deliver);
  }

}
