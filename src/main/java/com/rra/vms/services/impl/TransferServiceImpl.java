package com.rra.vms.services.impl;

import com.rra.vms.entities.PlateNumber;
import com.rra.vms.entities.Transfer;
import com.rra.vms.entities.VehicleOwner;
import com.rra.vms.enums.EPlateAvailability;
import com.rra.vms.exceptions.ResourceNotFoundException;
import com.rra.vms.mappers.TransferMapper;
import com.rra.vms.repository.PlateNumberRepository;
import com.rra.vms.repository.TransferRepository;
import com.rra.vms.repository.VehicleOwnerRepository;
import com.rra.vms.request.CreateTransferRequest;
import com.rra.vms.services.ITransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements ITransferService {
    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;
    private final VehicleOwnerRepository vehicleOwnerRepository;
    private final PlateNumberRepository plateNumberRepository;
    @Override
    public Transfer createTransfer(CreateTransferRequest createTransferRequest) {

        try {
            PlateNumber plateNumber= plateNumberRepository.findById(createTransferRequest.plateNumberId())
                    .orElseThrow(()-> new RuntimeException("PlateNumber not found"));
            VehicleOwner formerVehicleOwner= plateNumber.getOwner();

            VehicleOwner newVehicleOwner= vehicleOwnerRepository.findById(createTransferRequest.newOwnerId()).orElseThrow(
                    ()-> new ResourceNotFoundException("VehicleOwner not found")
            );
            Transfer transfer= transferMapper.toTransferEntity(createTransferRequest);
            transfer.setNewOwnerId(newVehicleOwner.getId());
            transfer.setFormerOwnerId(formerVehicleOwner.getId());

//            Update plate number details
            plateNumber.setOwner(newVehicleOwner);
            plateNumber.setAvailability(EPlateAvailability.IN_USE);
            return transferRepository.save(transfer);

        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Page<Transfer> getAllTransfers(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return transferRepository.findAll(pageable);
    }
}
