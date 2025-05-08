package com.rra.vms.services;

import com.rra.vms.entities.VehicleOwner;
import com.rra.vms.request.CreateVehicleOwnerRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface IVehicleOwnerService {
    VehicleOwner createVehicleOwner(CreateVehicleOwnerRequest vehicleOwnerRequest);

    Page<VehicleOwner> getAllVehicleOwners(int size, int page);
    VehicleOwner getVehicleOwnerById(UUID vehicleOwnerId);

    Page<VehicleOwner> searchVehicleOwners(String searchQuery,int size,int page);
}
