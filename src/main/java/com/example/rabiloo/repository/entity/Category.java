package com.example.rabiloo.repository.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type; // Ví dụ: technology, severity, department

    @Column(columnDefinition = "TEXT")
    private String description;

    // Quan hệ nhiều-nhiều với Issues
    @ManyToMany(mappedBy = "categories")
    private Set<Issue> issues = new HashSet<>();
}