package com.vertex.marketplace.controller.ba;

import com.vertex.marketplace.dto.ContractResponseDto;
import com.vertex.marketplace.entity.Contract;
import com.vertex.marketplace.mapper.ContractMapper;
import com.vertex.marketplace.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ba")
@PreAuthorize("hasRole('BA')")
@RequiredArgsConstructor
public class BaController {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    @GetMapping("/dashboard")
    public ResponseEntity<String> getBaDashboard() {
        return ResponseEntity.ok("Welcome to the Business Analyst Dashboard. Access to Developer PII is restricted.");
    }

    /**
     * BAs can view contracts, but they MUST see masked developer UUIDs.
     */
    @GetMapping("/contracts")
    public ResponseEntity<List<ContractResponseDto>> getAllContractsForBa() {
        List<Contract> contracts = contractRepository.findAll();
        List<ContractResponseDto> response = contracts.stream()
                .map(contractMapper::toMaskedDto) // Data masking logic applied here
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
