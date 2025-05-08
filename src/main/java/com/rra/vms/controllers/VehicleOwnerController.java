package com.rra.vms.controllers;


import com.rra.vms.entities.VehicleOwner;
import com.rra.vms.request.CreateVehicleOwnerRequest;
import com.rra.vms.response.ApiResponse;
import com.rra.vms.response.PagedResponse;
import com.rra.vms.services.impl.VehicleOwnerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vehicle-owners")
@RequiredArgsConstructor
public class VehicleOwnerController {

    private final VehicleOwnerServiceImpl vehicleOwnerService;
    private final VehicleOwnerServiceImpl vehicleOwnerServiceImpl;


    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<VehicleOwner>> createVehicleOwner(@RequestBody CreateVehicleOwnerRequest createVehicleOwnerRequest) {
        VehicleOwner response = vehicleOwnerService.createVehicleOwner(createVehicleOwnerRequest);
        return new ApiResponse<>("Vehicle Owner created successfully", HttpStatus.CREATED,response).toResponseEntity();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PagedResponse<VehicleOwner>>> getVehicleOwnersByPage(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<VehicleOwner> page= vehicleOwnerService.getAllVehicleOwners(size,pageNumber);

        PagedResponse<VehicleOwner> response = new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
        return new ApiResponse<>("Vehicle Owner list", HttpStatus.OK,response).toResponseEntity();
    }

    @GetMapping("/{vehicleOwnerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<VehicleOwner>> getVehicleOwner(@PathVariable("vehicleOwnerId") UUID vehicleOwnerId) {
        VehicleOwner response= vehicleOwnerServiceImpl.getVehicleOwnerById(vehicleOwnerId);
        return  new ApiResponse<>("Vehicle Owner found", HttpStatus.OK,response).toResponseEntity();
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PagedResponse<VehicleOwner>>>  searchVehicleOwner(
            @RequestParam(name = "q") String q ,
            @RequestParam(name = "size",defaultValue = "10") int size,
            @RequestParam(name = "page",defaultValue = "0") int pageNumber
    ){
        Page<VehicleOwner> page=vehicleOwnerService.searchVehicleOwners(q,size,pageNumber);
        PagedResponse<VehicleOwner> response = new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
        return new ApiResponse<>("Vehicle Owner list", HttpStatus.OK,response).toResponseEntity();

    }
}
