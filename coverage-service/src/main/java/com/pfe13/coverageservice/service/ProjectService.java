package com.pfe13.coverageservice.service;
import com.pfe13.coverageservice.dto.ProjectDto;
import com.pfe13.coverageservice.entity.Project;
import com.pfe13.coverageservice.exception.ProjectNotFoundException;


import java.util.List;

public interface ProjectService {
    ProjectDto save(ProjectDto projectDto);

    ProjectDto update(String id, ProjectDto ProjectDto) throws ProjectNotFoundException;

    ProjectDto getById(String id) throws ProjectNotFoundException;

    void deleteById(String id);
    List<Project> getAllProjects();


}
