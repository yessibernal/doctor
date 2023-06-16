package com.innter.pos.patients.DAOs;

import com.innter.pos.patients.entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDAO extends JpaRepository<DoctorEntity, Long> {

    @Query("SELECT d FROM DoctorEntity d WHERE d.id = :id")
    DoctorEntity getDoctor (@Param("id") Long id);

}
