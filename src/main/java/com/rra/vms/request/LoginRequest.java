package com.rra.vms.request;

public record LoginRequest(
        String email,
        String password
) {
}
