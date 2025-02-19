package com.example.rabiloo.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchIssueRequest {

    private List<Long> cateIds;

    private List<Long> projectIds;

    private List<Long> tagIds;

    private List<String> issueTypes;

    private Integer pageIndex = 1;

    private Integer pageSize = 5;

    private String keyword;

}
