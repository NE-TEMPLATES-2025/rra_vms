package com.rra.vms.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rra.vms.enums.EPlateAvailability;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "plate_numbers")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlateNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "plate_number",nullable = false,unique = true)
    private String plateNumber;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "plate_availability")
    private EPlateAvailability availability = EPlateAvailability.IN_USE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_owner_id",nullable = false)
    @JsonBackReference
    private VehicleOwner owner;


    @OneToOne(mappedBy = "plateNumber",cascade = CascadeType.ALL)
    @JsonIgnore
    private Vehicle vehicle;

}
