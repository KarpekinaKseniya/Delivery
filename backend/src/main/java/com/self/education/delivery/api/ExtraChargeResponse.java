package com.self.education.delivery.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtraChargeResponse {

    private float minWeight;
    private float maxWeight;
    private float charge;
}
