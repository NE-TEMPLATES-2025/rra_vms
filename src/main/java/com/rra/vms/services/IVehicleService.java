package com.rra.vms.services;

import com.rra.vms.entities.Vehicle;
import com.rra.vms.request.CreateVehicleRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IVehicleService {
    Vehicle createVehicle(CreateVehicleRequest createVehicleRequest);
    Vehicle getVehicle(UUID vehicleId);
    Page<Vehicle> getAllVehicles(int size, int page);
    Page<Vehicle> searchVehicles(String searchQuery,int size,int page);
}
