package com.rra.vms.repository;

import com.rra.vms.entities.VehicleOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner, UUID> {

    Page<VehicleOwner> findAll(Pageable pageable);

    @Query(
            value = "SELECT * FROM vehicle_owners vo " +
                    "WHERE LOWER(vo.phone_number) LIKE CONCAT('%', LOWER(:searchQuery), '%') " +
                    "OR CAST(vo.national_id AS TEXT) LIKE CONCAT('%', :searchQuery, '%') " +
                    "OR LOWER(vo.first_name) LIKE CONCAT('%', LOWER(:searchQuery), '%') " +
                    "OR LOWER(vo.last_name) LIKE CONCAT('%', LOWER(:searchQuery), '%')"
            ,
            nativeQuery = true
    )
    Page<VehicleOwner> search(Pageable pageable, @Param("searchQuery") String searchQuery);
}
