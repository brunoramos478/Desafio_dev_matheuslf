package com.desafio.matheuslf.shared.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record ProjectDto(
        @NotBlank
        String name,
        String description,
        @NotNull
        LocalDate startDate,
        @NotNull
        LocalDate endDate
) {
}
