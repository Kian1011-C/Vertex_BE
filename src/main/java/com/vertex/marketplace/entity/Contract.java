package com.vertex.marketplace.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "contracts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_number", nullable = false, unique = true)
    private String contractNumber;

    @Column(name = "startup_name", nullable = false)
    private String startupName;

    /**
     * Internal developer identifier. BAs should only see a masked version of this
     * (e.g., anonymized string prefix or custom obfuscated token), whereas TAs can see the full value.
     */
    @Column(name = "developer_id", nullable = false)
    private UUID developerId;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private BigDecimal value;
}
