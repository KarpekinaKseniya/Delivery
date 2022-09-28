package com.self.education.delivery.api;

import java.util.List;
import javax.validation.Valid;
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
    @Valid
    private List<ExtraChargeRequest> extraCharges;
}
