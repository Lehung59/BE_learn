package com.example.rabiloo.repository;

import com.example.rabiloo.repository.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Set<Project> findByProjectIdIn(Set<Long> longs);
}
