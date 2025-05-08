package com.rra.vms.services.impl;

import com.rra.vms.entities.VehicleOwner;
import com.rra.vms.exceptions.ResourceNotFoundException;
import com.rra.vms.mappers.VehicleOwnerMapper;
import com.rra.vms.repository.VehicleOwnerRepository;
import com.rra.vms.request.CreateVehicleOwnerRequest;
import com.rra.vms.services.IVehicleOwnerService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class VehicleOwnerServiceImpl implements IVehicleOwnerService {
    private final VehicleOwnerMapper vehicleOwnerMapper;
    private final VehicleOwnerRepository vehicleOwnerRepository;

    @Override
    public VehicleOwner createVehicleOwner(CreateVehicleOwnerRequest createVehicleOwnerRequest) {
        VehicleOwner vehicleOwner= vehicleOwnerMapper.toVehicleOwnerEntity(createVehicleOwnerRequest);
        return  vehicleOwnerRepository.save(vehicleOwner);
    }

    @Override
    public Page<VehicleOwner> getAllVehicleOwners(int size, int page) {
        Pageable pageable = PageRequest.of(page,size);
        return vehicleOwnerRepository.findAll(pageable);
    }

    @Override
    public VehicleOwner getVehicleOwnerById(UUID vehicleOwnerId) {
        Optional<VehicleOwner> user= vehicleOwnerRepository.findById(vehicleOwnerId);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("Vehicle Owner Not Found");
        }
        return user.get();
    }

    @Override
    public Page<VehicleOwner> searchVehicleOwners(String searchQuery, int size, int page) {
        Pageable pageable = PageRequest.of(page,size);
        return vehicleOwnerRepository.search(pageable,searchQuery);

    }


}
