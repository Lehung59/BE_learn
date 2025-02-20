package com.example.rabiloo.repository;

import com.example.rabiloo.repository.entity.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    Page<Issue> findAll(Specification<Issue> specification, Pageable pageable);

}
