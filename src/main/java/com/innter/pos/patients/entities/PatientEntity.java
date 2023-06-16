package com.innter.pos.patients.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Table(name = "tb_patients")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @NotNull
    @Column(name = "fi_file_number")
    private String fileNumber;

    @NotNull
    @Column(name = "fc_diagnostic")
    private String diagnostic;

    @NotNull
    @Column(name = "ft_status")
    private short status;

    @NotNull
    @Column(name = "fi_person_id")
    private int person;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_patients_doctors",
            joinColumns = @JoinColumn(name = "fi_patient_id"),
            inverseJoinColumns = @JoinColumn(name = "fi_doctor_id"))

    private List<DoctorEntity> doctors ;
}
