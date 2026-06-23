package com.vertex.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractResponseDto {
    private Long id;
    private String contractNumber;
    private String startupName;
    
    /**
     * String format developer ID. Depending on the requesting actor's permissions,
     * this will contain either the raw UUID or a masked/anonymized version.
     */
    private String developerId;
    
    private String details;
    private BigDecimal value;
}
