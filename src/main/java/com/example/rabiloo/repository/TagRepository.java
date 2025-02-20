package com.example.rabiloo.repository;

import com.example.rabiloo.repository.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Set<Tag> findByTagIdIn(Set<Long> listTagIds);
}
