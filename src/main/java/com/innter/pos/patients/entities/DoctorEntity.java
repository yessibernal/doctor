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
@Table(name = "tb_doctors")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;


    @NotNull
    @Column(name = "fc_name")
    private String name;

    @NotNull
    @Column(name = "fc_last_name")
    private String lastName;

    @NotNull
    @Column(name = "fc_surname")
    private String surname;

    @NotNull
    @Column(name = "fc_cedula")
    private String cedula;

    @NotNull
    @Column(name = "fc_phone")
    private String phone;

    @NotNull
    @Column(name = "fc_email")
    private String email;

    @NotNull
    @Column(name = "fc_specialty")
    private String specialty;

    @ManyToMany(mappedBy = "doctors")
    public List<PatientEntity> patients;
}
