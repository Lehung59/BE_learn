package com.example.rabiloo.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(unique = true, nullable = false)
    private String name;

    // Quan hệ nhiều-nhiều với Issues
    @ManyToMany(mappedBy = "tags")
    private Set<Issue> issues = new HashSet<>();
}