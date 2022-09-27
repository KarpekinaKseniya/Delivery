package com.self.education.delivery.api;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaRequest {

    private float baseCharge;
    private boolean hasDelivery;
    private List<ExtraChargeRequest> extraCharges;
}
