package com.desafio.matheuslf.adapter.in;

import com.desafio.matheuslf.application.service.ApplicationService;
import com.desafio.matheuslf.shared.dto.ProjectDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    private final ApplicationService service;

    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping()
    public ResponseEntity<ProjectDto> newProject(@Valid @RequestBody ProjectDto dto) {
        var response = service.createProject(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping()
    public ResponseEntity<List<ProjectDto>> listAllProject(Pageable pageable) {
        var response = service.listAllProjects(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response.toList());
    }
}
