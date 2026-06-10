package com.desafio.matheuslf.adapter.in;

import com.desafio.matheuslf.application.service.ApplicationService;
import com.desafio.matheuslf.shared.dto.TaskDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
