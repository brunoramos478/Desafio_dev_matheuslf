package com.desafio.matheuslf.shared.mapper;

import com.desafio.matheuslf.model.postgresql.ProjectEntity;
import com.desafio.matheuslf.shared.dto.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper mapping = Mappers.getMapper(ProjectMapper.class);

    ProjectDto toProjectDto(ProjectEntity project);

    @Mapping(target = "id", ignore = true)
    ProjectEntity toProjectEntity(ProjectDto projectDto);

}
