package com.desafio.matheuslf.shared.mapper;

import com.desafio.matheuslf.model.postgresql.TaskEntity;
import com.desafio.matheuslf.shared.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper mapping = Mappers.getMapper(TaskMapper.class);

    TaskDto toTaskDto(TaskEntity task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    TaskEntity toTaskEntity(TaskDto taskDto);

}
