package com.desafio.matheuslf.shared.dto;

import com.desafio.matheuslf.shared.enums.PriorityTask;
import com.desafio.matheuslf.shared.enums.StatusTask;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.util.UUID;

@Builder
public record TaskDto(
        @NotBlank
        String title,
        String description,
        @NotBlank
        StatusTask status,
        @NotNull
        PriorityTask priority,
        @NotNull
        UUID projectId
) {
}
