package com.example.rabiloo.repository;

import com.example.rabiloo.repository.entity.Attachment;
import com.example.rabiloo.repository.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {


    List<Attachment> findByIssue(Issue issue);
}
