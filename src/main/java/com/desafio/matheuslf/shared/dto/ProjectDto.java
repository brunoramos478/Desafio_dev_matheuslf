package com.desafio.matheuslf.shared.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record ProjectDto(
        UUID id,
        @NotBlank(message = "O nome do projeto é obrigatório")
        String name,
        String description,
        @NotNull(message = "A data de início do projeto é obrigatória")
        LocalDate startDate,
        @NotNull(message = "A data de término do projeto é obrigatória")
        LocalDate endDate
) {
}
