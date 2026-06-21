package com.desafio.matheuslf.application.service;

import com.desafio.matheuslf.model.postgresql.ProjectEntity;
import com.desafio.matheuslf.model.postgresql.TaskEntity;
import com.desafio.matheuslf.model.repository.ProjectRepository;
import com.desafio.matheuslf.model.repository.TaskRepository;
import com.desafio.matheuslf.shared.dto.TaskDto;
import com.desafio.matheuslf.shared.enums.PriorityTask;
import com.desafio.matheuslf.shared.enums.StatusTask;
import com.desafio.matheuslf.shared.exception.ProjectNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ApplicationService - Task Creation Tests")
class ApplicationServiceTests {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private ApplicationService applicationService;

    private UUID projectId;
    private UUID taskId;
    private ProjectEntity projectEntity;
    private TaskDto taskDto;

    @BeforeEach
    void setUp() {
        projectId = UUID.randomUUID();
        taskId = UUID.randomUUID();


        projectEntity = new ProjectEntity();
        projectEntity.setId(projectId);
        projectEntity.setName("Projeto Teste");
        projectEntity.setDescription("Esse é um projeto de desafio");

        taskDto = TaskDto.builder()
                .title("Teste automatizados")
                .description("Implementar nova feature no sistema do desafio")
                .status(StatusTask.TODO)
                .priority(PriorityTask.HIGH)
                .projectId(projectId)
                .build();
    }

    @Test
    @DisplayName("foi criado com sucesso quando o projeto existe")
    void shouldCreateTaskSuccessfullyWhenProjectExists() {

        when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(projectEntity));
        when(taskRepository.save(any(TaskEntity.class)))
                .thenAnswer(invocation -> {
                    TaskEntity task = invocation.getArgument(0);
                    task.setId(taskId);
                    return task;
                });

        TaskDto result = applicationService.createTask(taskDto);

        assertThat(result).isNotNull();
        assertThat(result.title()).isEqualTo("Teste automatizados");
        assertThat(result.description()).isEqualTo("Implementar nova feature no sistema do desafio");
        assertThat(result.status()).isEqualTo(StatusTask.TODO);
        assertThat(result.priority()).isEqualTo(PriorityTask.HIGH);
        assertThat(result.projectId()).isEqualTo(projectId);

        verify(projectRepository, times(1)).findById(projectId);
        verify(taskRepository, times(1)).save(any(TaskEntity.class));
    }

    @Test
    @DisplayName("deve vincular projeto corretamente na tarefa")
    void shouldLinkProjectCorrectlyToTask() {

        when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(projectEntity));
        when(taskRepository.save(any(TaskEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        applicationService.createTask(taskDto);

        verify(taskRepository).save(argThat(task ->
                task.getProject() != null &&
                task.getProject().getId().equals(projectId)
        ));
    }

    @Test
    @DisplayName("deve lançar exceção quando projeto não existe")
    void shouldThrowExceptionWhenProjectNotFound() {

        when(projectRepository.findById(projectId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> applicationService.createTask(taskDto))
                .isInstanceOf(ProjectNotFound.class)
                .hasMessage("Projeto não encontrado");


        verify(taskRepository, never()).save(any(TaskEntity.class));
    }

    @Test
    @DisplayName("foi incluido o projectId na resposta da criação")
    void shouldIncludeProjectIdInCreationResponse() {

        when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(projectEntity));
        when(taskRepository.save(any(TaskEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TaskDto result = applicationService.createTask(taskDto);

        assertThat(result.projectId()).isEqualTo(projectId);
    }

    @Test
    @DisplayName("foi mantido os mesmos atributos da tarefa ao criar")
    void shouldKeepAllTaskAttributesOnCreation() {

        when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(projectEntity));
        when(taskRepository.save(any(TaskEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        String customTitle = "Viajem para Lisboa";
        String customDescription = "Passagem e hospedagem já incluidas";
        StatusTask customStatus = StatusTask.DOING;
        PriorityTask customPriority = PriorityTask.HIGH;

        TaskDto customTaskDto = TaskDto.builder()
                .title(customTitle)
                .description(customDescription)
                .status(customStatus)
                .priority(customPriority)
                .projectId(projectId)
                .build();


        TaskDto result = applicationService.createTask(customTaskDto);

        assertThat(result.title()).isEqualTo(customTitle);
        assertThat(result.description()).isEqualTo(customDescription);
        assertThat(result.status()).isEqualTo(customStatus);
        assertThat(result.priority()).isEqualTo(customPriority);
    }

    @Test
    @DisplayName("foi chamado taskRepository.save() exatamente uma vez")
    void shouldCallTaskRepositorySaveExactlyOnce() {

        when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(projectEntity));
        when(taskRepository.save(any(TaskEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        applicationService.createTask(taskDto);

        verify(taskRepository, times(1)).save(any(TaskEntity.class));
    }
}



