package com.rra.vms.request;

import com.rra.vms.entities.Address;

public record CreateVehicleOwnerRequest(
        String firstName,
        String lastName,
        Long nationalId,
        String phoneNumber,
        Address address

) {
}
