package com.example.rabiloo.response;

import com.example.rabiloo.dto.FileDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IssueResponse {

    private Long id;

    private String title;

    private String description;

    @JsonProperty("root_cause")
    private String rootCause;

    private String solution;

    private String status;

    @JsonProperty("created_by_user_id")
    private Long createdByUserId;

    @JsonProperty("created_by_user_name")
    private String createdByUserName;

    @JsonProperty("reviewed_by_user_id")
    private Long reviewedByUserId;

    @JsonProperty("reviewed_by_user_name")
    private String reviewedByUserName;

    private List<String> tags;

    private List<String> categories;

    private List<String> projects;

    private List<FileDto> files;

    @JsonProperty("img_paths")
    private List<String> imgPaths;


}
