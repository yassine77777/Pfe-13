package com.pfe13.coverageservice.controller;
import com.pfe13.coverageservice.dao.ProjectDao;
import com.pfe13.coverageservice.dto.ProjectDto;
import com.pfe13.coverageservice.entity.Project;
import com.pfe13.coverageservice.exception.ProjectNotFoundException;
import com.pfe13.coverageservice.service.ProjectService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/primatec/projects")
public class ProjectRestController {
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectDao projectDao;


    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProjectDto save(@RequestBody ProjectDto projectDto) {
        return projectService.save(projectDto);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProjectDto update(@PathVariable String id, @RequestBody  ProjectDto projectDto) throws ProjectNotFoundException {
        return projectService.update(id, projectDto);
    }
    @GetMapping("/get/{id}")
    public ProjectDto getById(@PathVariable String id) throws ProjectNotFoundException {
        return projectService.getById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Project> getAllProjects(){
        List<Project> projects = projectService.getAllProjects();
        return projects.stream().toList();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable String id){
        projectService.deleteById(id);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(@NotNull Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
