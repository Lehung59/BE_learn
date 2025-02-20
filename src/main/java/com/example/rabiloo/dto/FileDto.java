package com.example.rabiloo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto {

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_type")
    private String fileType;

    @JsonProperty("file_path")
    private String filePath;

    @JsonProperty("file_size")
    private Long fileSize;

    @JsonProperty("created_at")
    private Long createdAt;

}
