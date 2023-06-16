package com.innter.pos.patients.DAOs;

import com.innter.pos.patients.entities.ExtensionImageBlackListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExtensionImageBlackListDAO extends JpaRepository<ExtensionImageBlackListEntity, Long> {
    @Query("SELECT i FROM ExtensionImageBlackListEntity i")
    List<ExtensionImageBlackListEntity> findByType();
}
