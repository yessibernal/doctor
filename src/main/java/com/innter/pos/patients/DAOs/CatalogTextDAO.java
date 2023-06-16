package com.innter.pos.patients.DAOs;

import com.innter.pos.patients.entities.CatalogTextEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogTextDAO extends JpaRepository<CatalogTextEntity, Long> {

    @Query("SELECT c FROM CatalogTextEntity c WHERE c.type = :type")
    List<CatalogTextEntity> getCatalogTextByType
            (@Param("type") String type,
             Pageable pageable
            );
}
