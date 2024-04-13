package com.pfe13.coverageservice.service.implementation;
import com.pfe13.coverageservice.dto.ProjectDto;
import com.pfe13.coverageservice.exception.ProjectNotFoundException;
import com.pfe13.coverageservice.service.ProjectService;
import com.pfe13.coverageservice.dao.ProjectDao;
import com.pfe13.coverageservice.entity.Project;
import com.pfe13.coverageservice.mapper.Mapper;
import com.pfe13.coverageservice.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    private static final String ALREADY_EXIST = "' already exist.";
    private static final String USER_WITH_EMAIL = "A user with email :'";


    @Autowired
    ProjectDao projectDao;
    Mapper mapper;
    public ProjectServiceImpl(ProjectDao projectDao, Mapper mapper) {
        this.mapper = mapper;
        this.projectDao = projectDao;

    }
    @Override
    public ProjectDto save(ProjectDto projectDto) {
        log.info("In save() :");

        Project project = mapper.fromProjectDto(projectDto);
        project.setId(UUID.randomUUID().toString());
        Project projectSaved = projectDao.save(project);
        log.info("customer saved successfully");
        return mapper.fromProject(projectSaved);
    }
    @Override
    public ProjectDto update(String id, ProjectDto userDto) throws ProjectNotFoundException {
        log.info("In update() :");
        Project user = projectDao.findById(id)
                .orElseThrow( ()-> new ProjectNotFoundException("user you try to update with id = '"+id+"' not found."));


        Project projectUpdate = projectDao.save(user);
        log.info("Project updated successfully");
        return mapper.fromProject(projectUpdate);
    }
    @Override
    public ProjectDto getById(String id) throws ProjectNotFoundException {
        log.info("In getById() :");
        Project project = projectDao.findById(id)
                .orElseThrow( ()-> new ProjectNotFoundException("user with id = '"+id+"' not found."));
        log.info("user with id = '"+id+"' found successfully.");
        return mapper.fromProject(project);
    }

    @Override
    public void deleteById(String id) {
        log.info("In deleteById() :");
        projectDao.deleteById(id);
        log.info("user deleted");
    }

    @Override
    public List<Project> getAllProjects() {
        return projectDao.findAll();
    }

 }
