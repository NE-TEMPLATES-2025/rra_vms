package com.rra.vms.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "vehicle_owners")
public class VehicleOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name ="national_id",nullable = false)
    private Integer nationalId;

    @Column(nullable = false,name = "phone_number")
    private String phoneNumber;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private List<PlateNumber> plateNumbers;

}
