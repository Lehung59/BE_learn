package com.example.rabiloo.repository;

import com.example.rabiloo.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> findByCategoryIdIn(Set<Long> listCateIds);
}
