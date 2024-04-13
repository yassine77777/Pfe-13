package com.pfe13.coverageservice.mapper.implementation;

import com.pfe13.coverageservice.dto.ProjectDto;
import com.pfe13.coverageservice.entity.Project;
import com.pfe13.coverageservice.mapper.Mapper;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MapperImpl implements Mapper {


    @Override
    public ProjectDto fromProject(@NonNull Project project) {
        return new ProjectDto(project.getId(), project.getName(), project.getDescription());
    }

    @Override
    public Project fromProjectDto(ProjectDto projectDto) {
        return Project.builder()
                .id(projectDto.id()).name(projectDto.name())
                .description(projectDto.description())
                .build();
    }

    @Override
    public List<ProjectDto> fromListOfProject(List<Project> projects) {
        return projects.stream().map(this::fromProject).toList();
    }
    public MapperImpl(){
        super();
    }
}
