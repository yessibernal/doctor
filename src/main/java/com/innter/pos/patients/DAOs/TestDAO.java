package com.innter.pos.patients.DAOs;

import com.innter.pos.patients.entities.TestEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDAO extends JpaRepository<TestEntity, Long> {
    @Query("SELECT n FROM TestEntity n WHERE n.testName = :testName")
    List<TestEntity> getTestByName(
            @Param("testName") String testName,
            Pageable pageable
    );
}
