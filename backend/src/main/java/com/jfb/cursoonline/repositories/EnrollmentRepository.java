package com.jfb.cursoonline.repositories;

import com.jfb.cursoonline.entities.Enrollment;
import com.jfb.cursoonline.entities.pk.EnrollmentPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentPK> {

}
