package com.self.education.delivery.validation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.self.education.delivery.helper.ExtraChargeHelper.averageExtraChargeRequest;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.self.education.delivery.api.ExtraChargeRequest;

class ExtraChargeRequestValidationTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldSuccessValidate() {
        final ExtraChargeRequest request = averageExtraChargeRequest();
        final Set<ConstraintViolation<ExtraChargeRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldReturnErrorWhenMaxWeightAreIncorrect() {
        //@formatter:off
        final ExtraChargeRequest request = ExtraChargeRequest.builder()
                .minWeight(1.09f)
                .maxWeight(-1)
                .charge(4.56f).build();
        //@formatter:on
        final Set<ConstraintViolation<ExtraChargeRequest>> violations = validator.validate(request);
        final String errorMessage = violations.iterator().next().getMessage();

        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("Max Weight must be greater than or equal to 0.00"));
    }

    @Test
    void shouldReturnErrorWhenMinWeightAreIncorrect() {
        //@formatter:off
        final ExtraChargeRequest request = ExtraChargeRequest.builder()
                .minWeight(-11.09f)
                .maxWeight(15)
                .charge(4.56f).build();
        //@formatter:on
        final Set<ConstraintViolation<ExtraChargeRequest>> violations = validator.validate(request);
        final String errorMessage = violations.iterator().next().getMessage();

        assertFalse(violations.isEmpty());
        assertThat(errorMessage, is("Min Weight must be greater than or equal to 0.00"));
    }
}
