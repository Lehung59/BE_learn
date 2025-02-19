package com.example.rabiloo.service.impl;

import com.example.rabiloo.repository.IssueRepository;
import com.example.rabiloo.request.AddIssueRequest;
import com.example.rabiloo.request.SearchIssueRequest;
import com.example.rabiloo.response.BaseResponse;
import com.example.rabiloo.response.IssueResponse;
import com.example.rabiloo.response.PagingResponse;
import com.example.rabiloo.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;

    @Override
    public BaseResponse<PagingResponse<IssueResponse>> getAllIssues(SearchIssueRequest request) {

        return null;
    }

    @Override
    public BaseResponse<IssueResponse> getIssueDetail(Long id) {
        return null;
    }

    @Override
    public BaseResponse<?> createIssue(AddIssueRequest request) {
        return null;
    }

    @Override
    public BaseResponse<?> updateIssue(AddIssueRequest request) {
        return null;
    }
}
