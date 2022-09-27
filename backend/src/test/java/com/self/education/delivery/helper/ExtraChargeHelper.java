package com.self.education.delivery.helper;

import com.self.education.delivery.api.ExtraChargeRequest;
import com.self.education.delivery.api.ExtraChargeResponse;
import com.self.education.delivery.domain.ExtraCharge;

public class ExtraChargeHelper {

    private static final float AVERAGE_CHARGE = 3.47f;
    private static final float MIN_CHARGE = 1.53f;
    private static final float AVERAGE_MIN_WEIGHT = 6.00f;
    private static final float AVERAGE_MAX_WEIGHT = 15.50f;

    private static final float MIN_WEIGHT = 1.25f;
    private static final float MIN_MAX_WEIGHT = 5.75f;

    public static ExtraCharge.ExtraChargeBuilder averageExtraChargeEntityBuilder() {
        //@formatter:off
        return ExtraCharge.builder()
                .id(1L)
                .charge(AVERAGE_CHARGE)
                .minWeight(AVERAGE_MIN_WEIGHT)
                .maxWeight(AVERAGE_MAX_WEIGHT);
        //@formatter:on
    }

    public static ExtraChargeRequest averageExtraChargeRequest() {
        //@formatter:off
        return ExtraChargeRequest.builder()
                //.id(1L)
                .charge(AVERAGE_CHARGE)
                .minWeight(AVERAGE_MIN_WEIGHT)
                .maxWeight(AVERAGE_MAX_WEIGHT)
                .build();
        //@formatter:on
    }

    public static ExtraChargeResponse averageExtraChargeResponse() {
        //@formatter:off
        return ExtraChargeResponse.builder()
                .charge(AVERAGE_CHARGE)
                .minWeight(AVERAGE_MIN_WEIGHT)
                .maxWeight(AVERAGE_MAX_WEIGHT)
                .build();
        //@formatter:on
    }

    public static ExtraCharge minExtraChargeEntity() {
        //@formatter:off
        return ExtraCharge.builder()
                .id(2L)
                .charge(MIN_CHARGE)
                .minWeight(MIN_WEIGHT)
                .maxWeight(MIN_MAX_WEIGHT)
                .build();
        //@formatter:on
    }

    public static ExtraChargeRequest minExtraChargeRequest() {
        //@formatter:off
        return ExtraChargeRequest.builder()
                .charge(MIN_CHARGE)
                .minWeight(MIN_WEIGHT)
                .maxWeight(MIN_MAX_WEIGHT)
                .build();
        //@formatter:on
    }

    public static ExtraChargeResponse minExtraChargeResponse() {
        //@formatter:off
        return ExtraChargeResponse.builder()
                .charge(MIN_CHARGE)
                .minWeight(MIN_WEIGHT)
                .maxWeight(MIN_MAX_WEIGHT)
                .build();
        //@formatter:on
    }
}
