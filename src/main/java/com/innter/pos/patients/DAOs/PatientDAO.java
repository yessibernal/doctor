package com.innter.pos.patients.DAOs;

import com.innter.pos.patients.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientDAO extends JpaRepository<PatientEntity,Long> {

    @Query("SELECT p FROM PatientEntity p WHERE p.id = :id AND p.status = 1")
    PatientEntity findPatientStatusById(Long id);
}
