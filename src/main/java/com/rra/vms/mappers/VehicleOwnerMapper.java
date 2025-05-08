package com.rra.vms.mappers;


import com.rra.vms.entities.Address;
import com.rra.vms.entities.VehicleOwner;
import com.rra.vms.request.CreateVehicleOwnerRequest;
import org.springframework.stereotype.Component;

@Component
public class VehicleOwnerMapper {

    public VehicleOwner toVehicleOwnerEntity(CreateVehicleOwnerRequest createVehicleOwnerRequest) {
        return VehicleOwner
                .builder()
                .firstName(createVehicleOwnerRequest.firstName())
                .lastName(createVehicleOwnerRequest.lastName())
                .nationalId(createVehicleOwnerRequest.nationalId())
                .phoneNumber(createVehicleOwnerRequest.phoneNumber())
                .address(
                        Address
                                .builder()
                                .district(createVehicleOwnerRequest.address().getDistrict())
                                .province(createVehicleOwnerRequest.address().getProvince())
                                .street(createVehicleOwnerRequest.address().getStreet())
                                .build()

                )

                .build();
    }
}
