package com.example.rabiloo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class PagingDTO {

    private Integer current;

    private Integer size;

    @JsonProperty(value = "total_pages")
    private Integer totalPages;

    @JsonProperty(value = "total_records")
    private Long totalRecords;
}
