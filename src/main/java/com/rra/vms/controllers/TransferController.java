package com.rra.vms.controllers;


import com.rra.vms.entities.Transfer;
import com.rra.vms.request.CreateTransferRequest;
import com.rra.vms.response.ApiResponse;
import com.rra.vms.response.PagedResponse;
import com.rra.vms.services.impl.TransferServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final TransferServiceImpl transferService;


    @PutMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Transfer>> createTransfer(@RequestBody CreateTransferRequest createTransferRequest) {
        Transfer response = transferService.createTransfer(createTransferRequest);
        return new ApiResponse<>("Transfer Created Successfully", HttpStatus.CREATED, response).toResponseEntity();
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PagedResponse<Transfer>>> getAllTransfers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size
    ){

        Page<Transfer> page= transferService.getAllTransfers(size, pageNumber);
        PagedResponse<Transfer> response= new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
        return new ApiResponse<>("Transfer List Successfully", HttpStatus.OK, response).toResponseEntity();

    }
}
