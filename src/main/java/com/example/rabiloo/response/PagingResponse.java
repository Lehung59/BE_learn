package com.example.rabiloo.response;

import com.example.rabiloo.dto.PagingDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class PagingResponse<T> {

    private PagingDTO paging;

    private List<T> items;

}
