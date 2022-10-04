package com.self.education.delivery.domain;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "area")
public class Area implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "areas_sequence_generator")
    @SequenceGenerator(name = "areas_sequence_generator", sequenceName = "areas_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "name", nullable = false, unique = true, updatable = false)
    private String name;
    @Column(name = "base_charge")
    private Float baseCharge;
    @Column(name = "has_delivery")
    private boolean hasDelivery;
    @OneToMany(mappedBy = "area", fetch = LAZY, cascade = ALL, orphanRemoval = true)
    private List<ExtraCharge> extraCharges;

    public void setExtraCharges(final List<ExtraCharge> extraCharges) {
        if (null != extraCharges) {
            extraCharges.forEach(charge -> charge.setArea(this));
        }
        this.extraCharges = extraCharges;
    }
}
