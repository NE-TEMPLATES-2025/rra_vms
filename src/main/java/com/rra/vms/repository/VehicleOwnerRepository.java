package com.rra.vms.repository;

import com.rra.vms.entities.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner, UUID> {
}
