package com.desafio.matheuslf.adapter.in;

import com.desafio.matheuslf.application.service.ApplicationService;
import com.desafio.matheuslf.shared.dto.TaskDto;
import com.desafio.matheuslf.shared.enums.PriorityTask;
import com.desafio.matheuslf.shared.enums.StatusTask;
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

    @PostMapping()
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto dto) {
        var response = service.createTask(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

/*
    @GetMapping("/{status}/{priority}/{id}")
    public ResponseEntity<Page<TaskDto>> searchTaskFilter(@RequestParam String status, @RequestParam PriorityTask priority, @RequestParam UUID id) {
        var response = service.searchTaskFilter(status, priority, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
*/

    @PutMapping("/{id}")
    public ResponseEntity<Void> taskUpdateStatus(@PathVariable UUID id, StatusTask status) {
        service.updateStatusTask(id, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        service.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
