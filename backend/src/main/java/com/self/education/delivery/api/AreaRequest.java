package com.self.education.delivery.api;

import java.util.List;
import com.self.education.delivery.domain.ExtraCharge;

public class AreaRequest {

    private String name;
    private float baseCharge;
    private boolean hasDelivery;
    private List<ExtraCharge> extraCharges;
}
