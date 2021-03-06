package com.jfb.cursoonline.services;

import java.util.LinkedHashSet;
import java.util.Set;

import com.jfb.cursoonline.dto.DeliverRevisionDTO;
import com.jfb.cursoonline.entities.Deliver;
import com.jfb.cursoonline.observers.DeliverRevisionObserver;
import com.jfb.cursoonline.repositories.DeliverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliverService {

  @Autowired
  private DeliverRepository repository;

  private Set<DeliverRevisionObserver> deliverRevisionObserver = new LinkedHashSet<>();

  @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
  @Transactional
  public void saveRevision(Long id, DeliverRevisionDTO dto) {
    Deliver deliver = repository.getOne(id);
    deliver.setStatus(dto.getStatus());
    deliver.setFeedback(dto.getFeedback());
    deliver.setCorrectCount(dto.getCorrectCount());
    repository.save(deliver);
    
    for (DeliverRevisionObserver observer : deliverRevisionObserver) {
      observer.onSaveRevision(deliver);
    }
  }

  public void subscribeDeliverRevisionObserver(DeliverRevisionObserver observer) {
    deliverRevisionObserver.add(observer);
  }

}
