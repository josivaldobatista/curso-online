package com.jfb.cursoonline.resources;

import javax.validation.Valid;

import com.jfb.cursoonline.dto.DeliverRevisionDTO;
import com.jfb.cursoonline.services.DeliverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/deliveries")
public class DeliverResource {

  @Autowired
  private DeliverService service;

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> saveRevision(@PathVariable @Valid Long id, @RequestBody DeliverRevisionDTO dto) {
    service.saveRevision(id, dto);
    return ResponseEntity.noContent().build();
  }
}
