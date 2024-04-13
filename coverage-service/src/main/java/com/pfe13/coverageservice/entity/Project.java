package com.pfe13.coverageservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "projects")
public class Project {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private  String description;

}
