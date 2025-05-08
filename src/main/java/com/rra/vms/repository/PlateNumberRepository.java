package com.rra.vms.repository;


import com.rra.vms.entities.PlateNumber;
import com.rra.vms.entities.VehicleOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlateNumberRepository extends JpaRepository<PlateNumber, UUID> {

    Page<PlateNumber> findByOwner(VehicleOwner owner, Pageable pageable);

}
