package com.rra.vms.controllers;


import com.rra.vms.entities.PlateNumber;
import com.rra.vms.entities.Vehicle;
import com.rra.vms.request.CreateVehicleRequest;
import com.rra.vms.response.ApiResponse;
import com.rra.vms.response.PagedResponse;
import com.rra.vms.services.impl.VehicleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleServiceImpl vehicleService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Vehicle>> createVehicle(@RequestBody CreateVehicleRequest createVehicleRequest) {
        Vehicle response=vehicleService.createVehicle(createVehicleRequest);
        return new ApiResponse<>("Vehicle Created Successfully", HttpStatus.CREATED,response).toResponseEntity();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<Vehicle>>> getVehicles(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int pageNumber

    ) {
        Page<Vehicle> page= vehicleService.getAllVehicles(size,pageNumber);
        PagedResponse<Vehicle> response = new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
        return new ApiResponse<>("Vehicle list retrieved successfully", HttpStatus.OK, response).toResponseEntity();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<Vehicle>> getVehicleBYId(
            @PathVariable("vehicleId") UUID vehicleId
    ) {
        Vehicle response=vehicleService.getVehicle(vehicleId);
        return new ApiResponse<>("Vehicle retrieved Successfully", HttpStatus.OK,response).toResponseEntity();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PagedResponse<Vehicle>>> searchVehicles(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(name = "q") String query

    ) {
        Page<Vehicle> page= vehicleService.searchVehicles(query,size,pageNumber);
        PagedResponse<Vehicle> response = new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
        return new ApiResponse<>("Vehicle list retrieved successfully", HttpStatus.OK, response).toResponseEntity();
    }
}
