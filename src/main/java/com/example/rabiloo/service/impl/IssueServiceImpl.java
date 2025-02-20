package com.example.rabiloo.service.impl;

import com.example.rabiloo.constant.CommonMessage;
import com.example.rabiloo.dto.FileDto;
import com.example.rabiloo.dto.PagingDTO;
import com.example.rabiloo.repository.*;
import com.example.rabiloo.repository.entity.*;
import com.example.rabiloo.request.AddIssueRequest;
import com.example.rabiloo.request.SearchIssueRequest;
import com.example.rabiloo.response.BaseResponse;
import com.example.rabiloo.response.IssueResponse;
import com.example.rabiloo.response.PagingResponse;
import com.example.rabiloo.service.IssueService;
import com.example.rabiloo.specification.IssueSpecification;
import com.example.rabiloo.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final AttachmentRepository attachmentRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    @Override
    public BaseResponse<PagingResponse<IssueResponse>> getAllIssues(SearchIssueRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPageIndex() - 1, request.getPageSize());
        Specification<Issue> issueSpecification = IssueSpecification.searchIssue(request);
        Page<Issue> issuePage = issueRepository.findAll(issueSpecification, pageRequest);
        List<IssueResponse> issueResponseList = issuePage.getContent().stream().map(t -> {
                    IssueResponse issueResponse = new IssueResponse();
                    issueResponse.setId(t.getIssueId());
                    issueResponse.setTitle(t.getTitle());
                    issueResponse.setDescription(t.getDescription());
                    issueResponse.setRootCause(t.getRootCauseAnalysis());
                    issueResponse.setSolution(t.getRecommendedSolutions());
                    issueResponse.setStatus(t.getStatus().status);
                    issueResponse.setCreatedByUserId(t.getCreatedBy().getUserId());
                    issueResponse.setCreatedByUserName(t.getCreatedBy().getName());
                    issueResponse.setReviewedByUserId(t.getReviewedBy().getUserId());
                    issueResponse.setReviewedByUserName(t.getReviewedBy().getName());
                    issueResponse.setTags(t.getTags().stream().map(Tag::getName).toList());
                    issueResponse.setCategories(t.getCategories().stream().map(Category::getName).toList());
                    issueResponse.setProjects(t.getProjects().stream().map(Project::getTitle).toList());
                    return issueResponse;
                }
        ).toList();
        PagingResponse<IssueResponse> response = new PagingResponse<>();
        response.setItems(issueResponseList);
        response.setPaging(PagingDTO.of(request.getPageIndex(), request.getPageSize(), issuePage.getTotalPages(), issuePage.getTotalElements()));
        return new BaseResponse<>(CommonMessage.SUCCESS, response);
    }

    @Override
    public BaseResponse<IssueResponse> getIssueDetail(Long id) {

        Issue issue = issueRepository.findById(id).orElse(null);
        if (issue == null) {
            return new BaseResponse<>(CommonMessage.FAILED, "Không tồn tại");
        }

        List<Attachment> attachment = attachmentRepository.findByIssue(issue);
        List<String> imgUrl = new ArrayList<>();
        List<FileDto> fileDtoList = attachment.stream()

                .map(t -> {
                    FileDto fileDto = new FileDto();
                    if (Objects.equals(t.getFileType(), "jpg") || Objects.equals(t.getFileType(), "png"))
                        imgUrl.add(t.getFilePath());
                    else {
                        fileDto.setFileName(t.getFileName());
                        fileDto.setFileType(t.getFileType());
                        fileDto.setFilePath(t.getFilePath());
                        fileDto.setFileSize(t.getFileSize());
                        fileDto.setCreatedAt(t.getCreatedAt());

                    }
                    return fileDto;
                }).toList();

        IssueResponse issueResponse = new IssueResponse();
        issueResponse.setId(issue.getIssueId());
        issueResponse.setTitle(issue.getTitle());
        issueResponse.setDescription(issue.getDescription());
        issueResponse.setRootCause(issue.getRootCauseAnalysis());
        issueResponse.setSolution(issue.getRecommendedSolutions());
        issueResponse.setStatus(issue.getStatus().status);
        issueResponse.setCreatedByUserId(issue.getCreatedBy().getUserId());
        issueResponse.setCreatedByUserName(issue.getCreatedBy().getName());
        issueResponse.setReviewedByUserId(issue.getReviewedBy().getUserId());
        issueResponse.setReviewedByUserName(issue.getReviewedBy().getName());
        issueResponse.setTags(issue.getTags().stream().map(Tag::getName).toList());
        issueResponse.setCategories(issue.getCategories().stream().map(Category::getName).toList());
        issueResponse.setProjects(issue.getProjects().stream().map(Project::getTitle).toList());
        issueResponse.setImgPaths(imgUrl);
        issueResponse.setFiles(fileDtoList);

        return new BaseResponse<>(CommonMessage.SUCCESS, issueResponse);
    }

    @Override
    public BaseResponse<?> createIssue(AddIssueRequest request) {

        User reviewer = new User();

        if (request.getReviewerId() != null) {
            reviewer = userRepository.findById(request.getReviewerId()).orElse(null);
            if (reviewer == null) {
                return new BaseResponse<>(CommonMessage.USER_NOT_FOUND);
            }
        }

        Set<Category> categories = categoryRepository.findByCategoryIdIn(new HashSet<>(request.getListCateIds()));
        Set<Tag> tags = tagRepository.findByTagIdIn(new HashSet<>(request.getListTagIds()));
        Set<Project> projects = projectRepository.findByProjectIdIn(new HashSet<>(request.getListPrjIds()));


        List<Attachment> attachments = new ArrayList<>();

        request.getFiles().forEach(file -> {
            Attachment attachment = new Attachment();
            attachment.setFileSize(file.getSize());
            attachment.setFileName(file.getName());

            String formatName = FileUtils.formatFileName(file);
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String path = "http://localhost:9000/api/files/" + formatName;

            attachment.setFileType(extension);
            attachment.setFileName(formatName);
            attachment.setFilePath(path);

            attachments.add(attachment);
            try {
                FileUtils.saveFile(file, formatName, "");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        request.getImage().forEach(image -> {
            Attachment attachment = new Attachment();
            attachment.setFileSize(image.getSize());
            attachment.setFileName(image.getName());
            String formatName = FileUtils.formatFileName(image);
            String extension = FilenameUtils.getExtension(image.getOriginalFilename());
            String path = "http://localhost:9000/api/files/" + formatName;

            attachment.setFileType(extension);
            attachment.setFileName(formatName);
            attachment.setFilePath(path);
            attachments.add(attachment);
            try {
                FileUtils.saveFile(image, formatName, "");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        Issue issue = new Issue();
        issue.setTitle(request.getTitle());
        issue.setDescription(request.getDescription());
        issue.setRootCauseAnalysis(request.getRootCauseAnalysis());
        issue.setRecommendedSolutions(request.getRecommendedSolutions());
        issue.setReviewedBy(request.getReviewerId() != null ? reviewer : null);
        issue.setCategories(categories);
        issue.setProjects(projects);
        issue.setTags(tags);
        issue = issueRepository.save(issue);
        Issue finalIssue = issue;

        attachments.forEach(attachment -> {
            attachment.setIssue(finalIssue);
        });
        attachmentRepository.saveAll(attachments);


        return new BaseResponse<>(CommonMessage.SUCCESS);
    }

    @Override
    public BaseResponse<?> updateIssue(AddIssueRequest request) {
        return null;
    }
}
