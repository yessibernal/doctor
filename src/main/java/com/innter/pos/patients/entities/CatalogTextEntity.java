package com.innter.pos.patients.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_catalogs_text")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogTextEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @NotNull
    @Column(name = "fc_type")
    private String type;

    @NotNull
    @Column(name = "fc_value")
    private String value;

}
