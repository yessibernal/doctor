package com.innter.pos.patients.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @Column(name = "fc_display_id")
    private String displayId;

    @Column(name = "fc_test_name")
    private String testName;

    @Column(name = "fc_report_name")
    private String reportName;

    @Column(name = "fc_delivery_time")
    private String deliveryTime;

    @Column(name = "fi_department_id")
    private int department;

    @Column(name = "fi_sample_type_id")
    private int sampleType;

    @Column(name = "fi_container_type_id")
    private int containerType;

    @Column(name = "fi_result_id")
    private int result;

    @Column(name = "fi_units_id")
    private int units;

    @Column(name = "fi_decimals_id")
    private int decimals;

    @Column(name = "fc_default_value")
    private String defaultValue;

    @Column(name = "fi_technique_used_id")
    private int techniqueUsed;

    @Column(name = "fi_maker_id")
    private int maker;

    @Column(name = "fc_lower_limit")
    private String lowerLimit;

    @Column(name = "fc_upper_limit")
    private String upperLimit;

    @Column(name = "fc_sat_key")
    private String satKey;

    @Column(name = "fc_sat_catalog")
    private String satCatalog;
}
