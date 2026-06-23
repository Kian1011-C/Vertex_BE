package com.vertex.marketplace.mapper;

import com.vertex.marketplace.dto.ContractResponseDto;
import com.vertex.marketplace.entity.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(target = "developerId", source = "developerId", qualifiedByName = "uuidToString")
    ContractResponseDto toDto(Contract contract);

    @Mapping(target = "developerId", source = "developerId", qualifiedByName = "maskUuid")
    ContractResponseDto toMaskedDto(Contract contract);

    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("maskUuid")
    default String maskUuid(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        String original = uuid.toString();
        // Mask the internal UUID to protect developer identity from BAs.
        // E.g., showing only the last 4 characters: "dev-****-****-xxxx"
        if (original.length() > 4) {
            return "dev-****-****-****-" + original.substring(original.length() - 4);
        }
        return "dev-****";
    }
}
