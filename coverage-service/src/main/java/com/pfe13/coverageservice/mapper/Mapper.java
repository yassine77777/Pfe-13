package com.pfe13.coverageservice.mapper;

import com.pfe13.coverageservice.dto.ProjectDto;
import com.pfe13.coverageservice.dao.ProjectDao;
import com.pfe13.coverageservice.entity.Project;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public interface Mapper {
    ProjectDto fromProject(Project project);

    Project fromProjectDto(ProjectDto projecDto);



    List<ProjectDto> fromListOfProject(List<Project> projects);
}
