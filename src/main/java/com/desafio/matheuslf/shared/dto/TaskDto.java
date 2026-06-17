package com.desafio.matheuslf.shared.dto;

import com.desafio.matheuslf.shared.enums.PriorityTask;
import com.desafio.matheuslf.shared.enums.StatusTask;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.util.UUID;

@Builder
public record TaskDto(
        @NotBlank(message = "O título da tarefa é obrigatório")
        String title,
        String description,
        @NotNull(message = "O status da tarefa é obrigatório")
        StatusTask status,
        @NotNull(message = "A prioridade da tarefa é obrigatória")
        PriorityTask priority,
        @NotNull(message = "O ID do projeto é obrigatório")
        UUID projectId
) {
}
