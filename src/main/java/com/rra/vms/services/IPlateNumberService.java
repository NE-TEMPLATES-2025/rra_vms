package com.rra.vms.services;

import com.rra.vms.entities.PlateNumber;
import com.rra.vms.request.CreatePlateNumberRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IPlateNumberService {

    PlateNumber createPlateNumber(CreatePlateNumberRequest createPlateNumberRequest);
    Page<PlateNumber> getPlateNumbersByOwnerId(UUID ownerId, int size,int page);
    Page<PlateNumber> getAllPlateNumbers(int size ,int page);
}
