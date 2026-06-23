package com.vertex.marketplace.controller.ta;

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
@RequestMapping("/api/v1/ta")
@PreAuthorize("hasRole('TA')")
@RequiredArgsConstructor
public class TaController {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    @GetMapping("/dashboard")
    public ResponseEntity<String> getTaDashboard() {
        return ResponseEntity.ok("Welcome to the Technical Assessor Dashboard. Full access to Developer profiles is authorized.");
    }

    /**
     * TAs can view contracts with the original, unmasked developer UUIDs.
     */
    @GetMapping("/contracts")
    public ResponseEntity<List<ContractResponseDto>> getAllContractsForTa() {
        List<Contract> contracts = contractRepository.findAll();
        List<ContractResponseDto> response = contracts.stream()
                .map(contractMapper::toDto) // Normal mapping showing raw UUIDs
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
