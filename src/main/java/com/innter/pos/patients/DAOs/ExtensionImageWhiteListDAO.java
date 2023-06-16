package com.innter.pos.patients.DAOs;

import com.innter.pos.patients.entities.ExtensionImageWhiteListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExtensionImageWhiteListDAO extends JpaRepository<ExtensionImageWhiteListEntity, Long> {
    @Query("SELECT i FROM ExtensionImageWhiteListEntity i")
    List<ExtensionImageWhiteListEntity> findByType();
}
