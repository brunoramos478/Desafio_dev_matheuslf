package com.desafio.matheuslf.model.repository;

import com.desafio.matheuslf.model.postgresql.TaskEntity;
import com.desafio.matheuslf.shared.enums.PriorityTask;
import com.desafio.matheuslf.shared.enums.StatusTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    @Query("SELECT t FROM TaskEntity t WHERE " +
            "(:status IS NULL OR t.status = :status) AND " +
            "(:priority IS NULL OR t.priority = :priority) AND " +
            "(:projectId IS NULL OR t.project.id = :projectId)")
    Page<TaskEntity> findByFilters(
            @Param("status") StatusTask status,
            @Param("priority") PriorityTask priority,
            @Param("projectId") UUID projectId,
            Pageable pageable);
}
