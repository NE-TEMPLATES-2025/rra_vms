package com.rra.vms.mappers;


import com.rra.vms.entities.Transfer;
import com.rra.vms.request.CreateTransferRequest;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper {


    public Transfer toTransferEntity(CreateTransferRequest createTransferRequest) {
       return Transfer
               .builder()
               .plateNumberId(createTransferRequest.plateNumberId())
               .amount(createTransferRequest.amount())
               .transferDate(createTransferRequest.date())
               .newOwnerId(createTransferRequest.newOwnerId())
               .build();
    }
}
