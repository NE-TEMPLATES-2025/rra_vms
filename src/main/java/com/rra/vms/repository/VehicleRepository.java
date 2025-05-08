package com.rra.vms.repository;


import com.rra.vms.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    @Query(
            value = "SELECT v FROM Vehicle  v "+
                    "WHERE lower(v.chassisNumber) LIKE CONCAT('%', lower(:searchQuery),'%')"+
                    "OR lower(v.plateNumber.plateNumber) LIKE CONCAT('%', lower(:searchQuery),'%')"
    )
    Page<Vehicle> search(@Param(value = "searchQuery") String searchQuery, Pageable pageable );
}
