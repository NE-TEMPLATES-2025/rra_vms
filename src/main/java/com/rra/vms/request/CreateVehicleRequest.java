package com.rra.vms.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public record CreateVehicleRequest(
        @NotBlank
        @NotEmpty
        String chassisNumber,
        @NotBlank
        @NotEmpty
        String manufacturer,
        @NotBlank
        @NotEmpty
        Integer manufactureYear,
        @NotBlank
        @NotEmpty
        String model,
        @NotBlank
        @NotEmpty
        Double price,
//        Relations
        UUID plateNumberId


) {
}
