package com.innter.pos.patients.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "tb_extensions_images_black_list")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExtensionImageBlackListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @NotNull
    @Column(name = "fc_type")
    private String type;
}
