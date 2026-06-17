package com.desafio.matheuslf.adapter.in;

import com.desafio.matheuslf.application.service.ApplicationService;
import com.desafio.matheuslf.shared.dto.TaskDto;
import com.desafio.matheuslf.shared.enums.PriorityTask;
import com.desafio.matheuslf.shared.enums.StatusTask;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final ApplicationService service;

    @ApiResponse(responseCode = "201", description = "Created, criando a task")
    @PostMapping()
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto dto) {
        var response = service.createTask(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

/*
    @ApiResponse(responseCode = "200", description = "OK, listando todas as tasks")
    @GetMapping("/{status}/{priority}/{id}")
    public ResponseEntity<Page<TaskDto>> searchTaskFilter(@RequestParam String status, @RequestParam PriorityTask priority, @RequestParam UUID id) {
        var response = service.searchTaskFilter(status, priority, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
*/

    @ApiResponse(responseCode = "200", description = "OK, atualizando o status da task")
    @PutMapping("/{id}")
    public ResponseEntity<Void> taskUpdateStatus(@PathVariable UUID id, @RequestParam StatusTask status) {
        service.updateStatusTask(id, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiResponse(responseCode = "204", description = "No Content, deletando a task")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        service.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
