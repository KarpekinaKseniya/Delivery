package com.self.education.delivery.domain;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "extra_charge")
public class ExtraCharge implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "extra_charges_sequence_generator")
    @SequenceGenerator(name = "extra_charges_sequence_generator", sequenceName = "extra_charges_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "min_weight", nullable = false)
    private float minWeight;
    @Column(name = "max_weight", nullable = false)
    private float maxWeight;
    @Column(name = "charge", nullable = false)
    private float charge;
    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;
}
