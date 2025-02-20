package com.example.rabiloo.specification;

import com.example.rabiloo.repository.entity.Issue;
import com.example.rabiloo.request.SearchIssueRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class IssueSpecification {

    public static Specification<Issue> searchIssue(SearchIssueRequest request) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getKeyword() != null && !request.getKeyword().isEmpty()) {
                Predicate getByKeyword = cb.like(root.get("title"), "%" + request.getKeyword().trim() + "%");
                predicates.add(getByKeyword);
            }

            if (cq != null)
                cq.orderBy(cb.desc(root.get("id")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
