package com.rra.vms.services;

import com.rra.vms.entities.Transfer;
import com.rra.vms.request.CreateTransferRequest;
import org.springframework.data.domain.Page;

public interface ITransferService {
    Transfer createTransfer(CreateTransferRequest createTransferRequest);
    Page<Transfer> getAllTransfers(int size, int page);
}
