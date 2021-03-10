package com.jfb.cursoonline.repositories;

import com.jfb.cursoonline.entities.Deliver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverRepository extends JpaRepository<Deliver, Long> {

}
