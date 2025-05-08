package com.rra.vms.services;

import com.rra.vms.entities.PlateNumber;
import com.rra.vms.request.CreatePlateNumberRequest;

public interface IPlateNumberService {

    PlateNumber createPlateNumber(CreatePlateNumberRequest createPlateNumberRequest);
}
