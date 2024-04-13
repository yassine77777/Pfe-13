package com.pfe13.coverageservice.dao;

import com.pfe13.coverageservice.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjectDao extends JpaRepository<Project, String> {


    @Query(value = "select * from projects where name = ?1", nativeQuery = true)
    Optional<Project> findByName(String name);





}
