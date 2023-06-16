package com.innter.pos.patients.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_test_instructions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestInstructionEntity {

    @Id
    @Column(name = "fi_id_test")
    private Long id;

    @NotNull
    @Column(name = "fc_patient_instructions")
    private String patientInstructions;

    @NotNull
    @Column(name = "fc_instructions")
    private String instructions;

    @NotNull
    @Column(name = "fc_exam_description")
    private String examDescription;
}
