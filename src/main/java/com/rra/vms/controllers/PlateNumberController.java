package com.rra.vms.controllers;


import com.rra.vms.entities.PlateNumber;
import com.rra.vms.request.CreatePlateNumberRequest;
import com.rra.vms.response.ApiResponse;
import com.rra.vms.response.PagedResponse;
import com.rra.vms.services.impl.PlateNumberServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/plate-number")
@RequiredArgsConstructor
public class PlateNumberController {
    private final PlateNumberServiceImpl plateNumberService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PlateNumber>> createPlateNumber(@RequestBody @Valid CreatePlateNumberRequest createPlateNumberRequest){
        PlateNumber response= plateNumberService.createPlateNumber(createPlateNumberRequest);
        return new ApiResponse<>("Plate number created successfully", HttpStatus.CREATED,response).toResponseEntity();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/owner/{ownerId}")
    public  ResponseEntity<ApiResponse<PagedResponse<PlateNumber>>> getPlateNumbersByOwner(
            @PathVariable("ownerId") UUID ownerId,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int pageNumber

    ){
        Page<PlateNumber> page= plateNumberService.getPlateNumbersByOwnerId(ownerId,size,pageNumber);
        PagedResponse<PlateNumber> response = new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );

        return new ApiResponse<>("Plate number list retrieved successfully", HttpStatus.OK, response).toResponseEntity();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public  ResponseEntity<ApiResponse<PagedResponse<PlateNumber>>> getAllPlateNumbers(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int pageNumber
    ){
        Page<PlateNumber> page= plateNumberService.getAllPlateNumbers(size,pageNumber);
        PagedResponse<PlateNumber> response = new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
        return  new ApiResponse<>("Plate number list retrieved successfully", HttpStatus.OK, response).toResponseEntity();
    }
}
