package com.innter.pos.patients.DAOs;

import com.innter.pos.patients.entities.CatalogTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogTypeDAO extends JpaRepository<CatalogTypeEntity, Long> {
    @Query("SELECT t FROM CatalogTypeEntity t WHERE t.type = :type")
    CatalogTypeEntity findByType(@Param("type") String type);
}
