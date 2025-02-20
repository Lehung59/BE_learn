package com.example.rabiloo.repository.entity;


import com.example.rabiloo.constant.IssueStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "issues")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Long issueId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "root_cause_analysis", columnDefinition = "TEXT")
    private String rootCauseAnalysis;

    @Column(name = "recommended_solutions", columnDefinition = "TEXT")
    private String recommendedSolutions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueStatus status;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "reviewed_by")
    private User reviewedBy;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    // Quan hệ nhiều-nhiều với Categories
    @ManyToMany
    @JoinTable(
            name = "issue_categories",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "issue_project",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects = new HashSet<>();


    // Quan hệ nhiều-nhiều với Tags
    @ManyToMany
    @JoinTable(
            name = "issue_tags",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    // Quan hệ 1-nhiều với Attachments
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private Set<Attachment> attachments = new HashSet<>();

    // Quan hệ 1-nhiều với Comments
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = System.currentTimeMillis();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = System.currentTimeMillis();
    }
}