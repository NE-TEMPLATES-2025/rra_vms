package com.rra.vms.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "transfers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "former_owner_id")
    private UUID formerOwnerId;

    @Column(name = "new_owner_id")
    private UUID newOwnerId;

    private LocalDateTime transferDate;

    @Column(name = "plate_number_id")
    private UUID plateNumberId;


}
