package com.rra.vms.request;


import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.UUID;

@Validated
public record CreatePlateNumberRequest(

        UUID ownerId,
        @Pattern(regexp = "^RA[A-J][0-9]{3}[A-Z]$")
        String plateNumber,
        LocalDate issueDate
) {
}
