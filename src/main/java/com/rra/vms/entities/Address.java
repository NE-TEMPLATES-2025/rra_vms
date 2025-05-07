package com.rra.vms.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String street;
    private String district;
    private String province;


}
