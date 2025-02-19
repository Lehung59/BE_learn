package com.example.rabiloo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class AddIssueRequest {

    private Long id;

    private String title;

    private String description;

    @JsonProperty("root_cause")
    private String rootCauseAnalysis;

    @JsonProperty("solution")
    private String recommendedSolutions;

    @JsonProperty("reviewer_id")
    private Long reviewerId;

    @JsonProperty("list_category_ids")
    private List<Long> listCateIds;

    @JsonProperty("list_prj_ids")
    private List<Long> listPrjIds;

    @JsonProperty("list_tag_ids")
    private List<Long> listTagIds;

    private List<MultipartFile> files;

    private List<MultipartFile> image;
}
