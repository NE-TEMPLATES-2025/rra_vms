package com.rra.vms.mappers;


import com.rra.vms.entities.PlateNumber;
import com.rra.vms.enums.EPlateAvailability;
import com.rra.vms.request.CreatePlateNumberRequest;
import org.springframework.stereotype.Component;

@Component
public class PlateNumberMapper {
    public PlateNumber toPlateNumberEntity(CreatePlateNumberRequest createPlateNumberRequest){
        return PlateNumber
                .builder()
                .plateNumber(createPlateNumberRequest.plateNumber())
                .issueDate(createPlateNumberRequest.issueDate())
                .availability(EPlateAvailability.IN_USE)
                .build();
    }
}
