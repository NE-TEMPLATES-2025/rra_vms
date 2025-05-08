package com.rra.vms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vehicles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false,name = "chassis_number")
    private String chassisNumber;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "manufacture_year",nullable = false)
    private Integer manufactureYear;

    @Column(name = "price",nullable = false)
    private Double price;

    @Column(name = "vehicle_model",nullable = false)
    private String vehicleModel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plate_number_id",referencedColumnName = "id")
    private PlateNumber plateNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_owner_id",nullable = false)
    @JsonBackReference
    private VehicleOwner vehicleOwner;
}
