package com.self.education.delivery.api;

import javax.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtraChargeRequest {

    @DecimalMin(value = "0.00", message = "Min Weight must be greater than or equal to 0.00")
    private float minWeight;
    @DecimalMin(value = "0.00", message = "Max Weight must be greater than or equal to 0.00")
    private float maxWeight;
    private float charge;
}
