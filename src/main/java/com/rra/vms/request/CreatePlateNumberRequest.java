package com.rra.vms.request;

import java.time.LocalDate;
import java.util.UUID;

public record CreatePlateNumberRequest(
        UUID ownerId,
        String plateNumber,
        LocalDate issueDate
) {
}
