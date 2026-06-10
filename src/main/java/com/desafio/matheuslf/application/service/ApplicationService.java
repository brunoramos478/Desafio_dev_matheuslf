package com.desafio.matheuslf.application.service;

import com.desafio.matheuslf.model.postgresql.ProjectEntity;
import com.desafio.matheuslf.model.postgresql.TaskEntity;
import com.desafio.matheuslf.model.repository.ProjectRepository;
import com.desafio.matheuslf.model.repository.TaskRepository;
import com.desafio.matheuslf.shared.dto.ProjectDto;
import com.desafio.matheuslf.shared.dto.TaskDto;
import com.desafio.matheuslf.shared.enums.StatusTask;
import com.desafio.matheuslf.shared.exception.InvalidDate;
import com.desafio.matheuslf.shared.mapper.ProjectMapper;
import com.desafio.matheuslf.shared.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ProjectDto createProject(ProjectDto dto) {

        if (dto.endDate().isBefore(dto.startDate())) {
            throw new InvalidDate();
        }

        ProjectEntity project = ProjectMapper.mapping.toProjectEntity(dto);
        projectRepository.save(project);

        return ProjectDto.builder()
                .name(dto.name())
                .description(dto.description())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .build();
    }

    public TaskDto createTask(TaskDto dto) {

        TaskEntity task = TaskMapper.mapping.toTaskEntity(dto);
        ProjectEntity project = projectRepository.findById(dto.projectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        task.setProject(project);
        taskRepository.save(task);

        return TaskDto.builder()
                .title(dto.title())
                .description(dto.description())
                .status(dto.status())
                .priority(dto.priority())
                .build();
    }

    public Page<ProjectDto> listAllProjects(Pageable pageable) {
        Page<ProjectEntity> projects = projectRepository.findAll(pageable);
        return projects.map(ProjectMapper.mapping::toProjectDto);
    }

    public void deleteTask(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }

        taskRepository.deleteById(id);
    }

    public void updateStatusTask(UUID id, StatusTask status) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(status);
        taskRepository.save(task);

    }

}