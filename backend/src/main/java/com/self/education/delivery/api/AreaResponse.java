package com.self.education.delivery.api;

import java.util.List;
import com.self.education.delivery.domain.ExtraCharge;

public class AreaResponse {

    private Long id;
    private String name;
    private float baseCharge;
    private boolean hasDelivery;
    private List<ExtraCharge> extraCharges;
}
