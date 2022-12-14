package com.self.education.delivery.helper;

import static java.lang.Boolean.FALSE;
import static com.self.education.delivery.helper.ExtraChargeHelper.averageExtraChargeEntityBuilder;
import static com.self.education.delivery.helper.ExtraChargeHelper.averageExtraChargeRequest;
import static com.self.education.delivery.helper.ExtraChargeHelper.averageExtraChargeResponse;
import static com.self.education.delivery.helper.ExtraChargeHelper.minExtraChargeEntity;
import static com.self.education.delivery.helper.ExtraChargeHelper.minExtraChargeRequest;
import static com.self.education.delivery.helper.ExtraChargeHelper.minExtraChargeResponse;

import java.util.List;
import com.self.education.delivery.api.AreaRequest;
import com.self.education.delivery.api.AreaResponse;
import com.self.education.delivery.domain.Area;

public class AreaHelper {

    private static final float BASE_CHARGE = 4.56f;
    public static final String AREA_NAME = "Bulawayo";
    public static final Long ID = 1L;

    public static Area.AreaBuilder areaEntityBuilder() {
        //@formatter:off
        return Area.builder()
                .id(ID)
                .name(AREA_NAME)
                .baseCharge(BASE_CHARGE)
                .extraCharges(List.of(averageExtraChargeEntityBuilder().build(), minExtraChargeEntity()))
                .hasDelivery(FALSE);
        //@formatter:on
    }

    public static AreaRequest.AreaRequestBuilder areaRequestBuilder() {
        //@formatter:off
        return AreaRequest.builder()
                .baseCharge(BASE_CHARGE)
                .extraCharges(List.of(averageExtraChargeRequest(), minExtraChargeRequest()))
                .hasDelivery(FALSE);
                //.name(NAME);
        //@formatter:on
    }

    public static AreaResponse.AreaResponseBuilder areaResponseBuilder() {
        //@formatter:off
        return AreaResponse.builder()
                .id(ID)
                .name(AREA_NAME)
                .baseCharge(BASE_CHARGE)
                .extraCharges(List.of(averageExtraChargeResponse(), minExtraChargeResponse()))
                .hasDelivery(FALSE);
        //@formatter:on
    }
}
