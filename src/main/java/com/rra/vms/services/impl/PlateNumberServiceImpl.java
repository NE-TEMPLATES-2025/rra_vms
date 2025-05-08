package com.rra.vms.services.impl;

import com.rra.vms.entities.PlateNumber;
import com.rra.vms.entities.Vehicle;
import com.rra.vms.entities.VehicleOwner;
import com.rra.vms.exceptions.ResourceNotFoundException;
import com.rra.vms.mappers.PlateNumberMapper;
import com.rra.vms.repository.PlateNumberRepository;
import com.rra.vms.repository.VehicleOwnerRepository;
import com.rra.vms.request.CreatePlateNumberRequest;
import com.rra.vms.services.IPlateNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PlateNumberServiceImpl implements IPlateNumberService {
    private final PlateNumberMapper plateNumberMapper;
    private final PlateNumberRepository plateNumberRepository;
    private final VehicleOwnerRepository vehicleOwnerRepository;


    @Override
    public PlateNumber createPlateNumber(CreatePlateNumberRequest createPlateNumberRequest) {
        Optional<VehicleOwner> vehicleOwner= vehicleOwnerRepository.findById(createPlateNumberRequest.ownerId());
        if (vehicleOwner.isPresent()){
            PlateNumber plateNumber= plateNumberMapper.toPlateNumberEntity(createPlateNumberRequest);
            plateNumber.setOwner(vehicleOwner.get());
            return plateNumberRepository.save(plateNumber);
        }
        else {
            throw new ResourceNotFoundException("Vehicle owner not found with id "+createPlateNumberRequest.ownerId());
        }
    }

    @Override
    public Page<PlateNumber> getPlateNumbersByOwnerId(UUID ownerId, int size,int page) {
        Pageable pageable= PageRequest.of(page,size);
        Optional<VehicleOwner> vehicleOwner= vehicleOwnerRepository.findById(ownerId);
        if (vehicleOwner.isPresent()){
            return plateNumberRepository.findByOwner(vehicleOwner.get(),pageable);
        }
        else {
            throw new ResourceNotFoundException("Vehicle owner not found with id "+ownerId);
        }
    }

    @Override
    public Page<PlateNumber> getAllPlateNumbers(int size, int page) {
        Pageable pageable=PageRequest.of(page,size);
        return plateNumberRepository.findAll(pageable);
    }
}
