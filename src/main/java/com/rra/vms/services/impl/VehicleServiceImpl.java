package com.rra.vms.services.impl;

import com.rra.vms.entities.PlateNumber;
import com.rra.vms.entities.Vehicle;
import com.rra.vms.exceptions.ResourceNotFoundException;
import com.rra.vms.mappers.VehicleMapper;
import com.rra.vms.repository.PlateNumberRepository;
import com.rra.vms.repository.VehicleRepository;
import com.rra.vms.request.CreateVehicleRequest;
import com.rra.vms.services.IVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements IVehicleService {

    private final VehicleRepository vehicleRepository;
    private final PlateNumberRepository plateNumberRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public Vehicle createVehicle(CreateVehicleRequest createVehicleRequest) {
        try {
            Optional<PlateNumber> _plateNumber= plateNumberRepository.findById(createVehicleRequest.plateNumberId());
            if (_plateNumber.isPresent()) {
                 PlateNumber plateNumber = _plateNumber.get();
                Vehicle vehicle= vehicleMapper.toVehicleEntity(createVehicleRequest);
                vehicle.setPlateNumber(plateNumber);
                vehicle.setVehicleOwner(plateNumber.getOwner());
                return vehicleRepository.save(vehicle);

            }
            else {
             throw new Exception("PlateNumber not found");
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public Vehicle getVehicle(UUID vehicleId) {
        return  vehicleRepository.findById(vehicleId).orElseThrow(
                ()->new ResourceNotFoundException("Vehicle not found")
        );

    }

    @Override
    public Page<Vehicle> getAllVehicles(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return vehicleRepository.findAll(pageable);
    }

    @Override
    public Page<Vehicle> searchVehicles(String searchQuery, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return vehicleRepository.search(searchQuery,pageable);
    }
}
