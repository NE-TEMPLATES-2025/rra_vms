package com.rra.vms.request;

import com.rra.vms.enums.ERole;

public record CreateRoleRequest(
        ERole name
) {
}
