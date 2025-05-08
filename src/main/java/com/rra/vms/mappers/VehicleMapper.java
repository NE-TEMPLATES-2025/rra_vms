package com.rra.vms.mappers;


import com.rra.vms.entities.Vehicle;
import com.rra.vms.request.CreateVehicleRequest;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public Vehicle toVehicleEntity(CreateVehicleRequest createVehicleRequest) {
        return  Vehicle
                .builder()
                .chassisNumber(createVehicleRequest.chassisNumber())
                .manufacturer(createVehicleRequest.manufacturer())
                .manufactureYear(createVehicleRequest.manufactureYear())
                .vehicleModel(createVehicleRequest.model())
                .price(createVehicleRequest.price())
                .build();
    }
}
