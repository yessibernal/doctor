package com.innter.pos.patients.DAOs;

import com.innter.pos.patients.entities.TestInstructionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestInstructionDAO extends JpaRepository<TestInstructionEntity, Long> {
}
