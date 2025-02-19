package com.example.rabiloo.service;

import com.example.rabiloo.request.AddIssueRequest;
import com.example.rabiloo.request.SearchIssueRequest;
import com.example.rabiloo.response.BaseResponse;
import com.example.rabiloo.response.IssueResponse;
import com.example.rabiloo.response.PagingResponse;

public interface IssueService {
    
    BaseResponse<PagingResponse<IssueResponse>> getAllIssues(SearchIssueRequest request);

    BaseResponse<IssueResponse> getIssueDetail(Long id);

    BaseResponse<?> createIssue(AddIssueRequest request);

    BaseResponse<?> updateIssue(AddIssueRequest request);
}
