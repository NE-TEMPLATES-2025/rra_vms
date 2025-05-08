package com.rra.vms.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTransferRequest(
        UUID newOwnerId,
        Double amount,
        UUID plateNumberId,
        LocalDateTime date


) {
}
