package com.example.rabiloo.controller;

import com.example.rabiloo.constant.CommonMessage;
import com.example.rabiloo.request.AddIssueRequest;
import com.example.rabiloo.request.SearchIssueRequest;
import com.example.rabiloo.response.BaseResponse;
import com.example.rabiloo.response.IssueResponse;
import com.example.rabiloo.response.PagingResponse;
import com.example.rabiloo.service.IssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/api/issue")
@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping("/list")
    public BaseResponse<PagingResponse<IssueResponse>> getAllIssues(
            @RequestParam(required = false) String keyword,
            @RequestParam(value = "page_index", required = false) Integer pageIndex,
            @RequestParam(value = "page_size", required = false) Integer pageSize,
            @RequestParam(value = "cate_ids", required = false) List<Long> cateIds,
            @RequestParam(value = "project_ids", required = false) List<Long> projectIds,
            @RequestParam(value = "tag_ids", required = false) List<Long> tagIds,
            @RequestParam(value = "issue_type", required = false) List<String> issueTypes




    ) {
        try {
            SearchIssueRequest request = new SearchIssueRequest();
            request.setKeyword(keyword);
            request.setPageIndex(pageIndex);
            request.setPageSize(pageSize);
            request.setCateIds(cateIds);
            request.setProjectIds(projectIds);
            request.setTagIds(tagIds);
            request.setIssueTypes(issueTypes);

            return issueService.getAllIssues(request);

        } catch (Exception e) {
            log.error("Loi xay ra: {} - trace: ", e.getMessage(), e);
        }
        return new BaseResponse<>(CommonMessage.FAILED);
    }

    @GetMapping("/{id}")
    public BaseResponse<IssueResponse> getIssueDetail(@PathVariable Long id) {

        try {

            return issueService.getIssueDetail(id);

        } catch (Exception e) {
            log.error("Loi xay ra: {} - trace: ", e.getMessage(), e);
        }
        return new BaseResponse<>(CommonMessage.FAILED);

    }

    @PostMapping()
    public BaseResponse<?> createIssue(@RequestParam(required = false) List<MultipartFile> files,
                                       @RequestParam(required = false) List<MultipartFile> image,
                                       @RequestParam String title,
                                       @RequestParam String description,
                                       @RequestParam String rootCauseAnalysis,
                                       @RequestParam String recommendedSolutions,
                                       @RequestParam(required = false) Long reviewerId,
                                       @RequestParam(required = false)List<Long> listCateIds,
                                       @RequestParam(required = false)List<Long> listPrjIds,
                                       @RequestParam(required = false) List<Long> listTagIds
                                       ) {
        try {

            AddIssueRequest request = new AddIssueRequest();
            request.setTitle(title);
            request.setDescription(description);
            request.setRootCauseAnalysis(rootCauseAnalysis);
            request.setRecommendedSolutions(recommendedSolutions);
            request.setReviewerId(reviewerId);
            request.setListCateIds(listCateIds);
            request.setListPrjIds(listPrjIds);
            request.setListTagIds(listTagIds);
            request.setFiles(files);
            request.setImage(image);

            return issueService.createIssue(request);
        } catch (Exception e) {
            log.error("Loi xay ra: {} - trace: ", e.getMessage(), e);
        }
        return new BaseResponse<>(CommonMessage.FAILED);
    }

    @PutMapping("/{id}")
    public BaseResponse<?> updateIssue(@PathVariable Long id,
                                       @RequestParam(required = false) List<MultipartFile> files,
                                       @RequestParam(required = false) List<MultipartFile> image,
                                       @RequestParam String title,
                                       @RequestParam String description,
                                       @RequestParam String rootCauseAnalysis,
                                       @RequestParam String recommendedSolutions,
                                       @RequestParam(required = false) Long reviewerId,
                                       @RequestParam(required = false)List<Long> listCateIds,
                                       @RequestParam(required = false)List<Long> listPrjIds,
                                       @RequestParam(required = false) List<Long> listTagIds) {
        try {
            AddIssueRequest request = new AddIssueRequest();
            request.setId(id);
            request.setTitle(title);
            request.setDescription(description);
            request.setRootCauseAnalysis(rootCauseAnalysis);
            request.setRecommendedSolutions(recommendedSolutions);
            request.setReviewerId(reviewerId);
            request.setListCateIds(listCateIds);
            request.setListPrjIds(listPrjIds);
            request.setListTagIds(listTagIds);
            request.setFiles(files);
            request.setImage(image);

            return issueService.updateIssue(request);
        } catch (Exception e) {
            log.error("Loi xay ra: {} - trace: ", e.getMessage(), e);
        }
        return new BaseResponse<>(CommonMessage.FAILED);

    }


}
