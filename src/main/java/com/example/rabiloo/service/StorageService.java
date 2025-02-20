package com.example.rabiloo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

@Service
@Slf4j
public class StorageService {

    public ResponseEntity<?> viewFile(String pathX) throws IOException {

        File file2Upload = new File(pathX);

        String mimetype = new MimetypesFileTypeMap().getContentType(file2Upload.getName());

        log.info("mimetype: {}", mimetype);
        int typeF = mimetype.contains("image") ? 1 : 0;
        String contentType = getContentType(FilenameUtils.getExtension(file2Upload.getName()));

        HttpHeaders headers = new HttpHeaders();

        headers.add("content-length", String.valueOf(Files.size(file2Upload.toPath())));
        headers.add("content-disposition", "inline; filename=\"" + file2Upload.getName() + "\"");

        InputStreamResource inputStream = new InputStreamResource(new FileInputStream(file2Upload));
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(typeF == 1 ? MediaType.IMAGE_JPEG : !Objects.equals(contentType, "") ? MediaType.parseMediaType(contentType) : MediaType.parseMediaType("application/octet-stream"))
                .body(inputStream);
    }

    private static String getContentType(String typeView) {
        String contentType = "";
        if (typeView.equalsIgnoreCase("mp4")) contentType = "video/mp4";
        if (typeView.equalsIgnoreCase("flv")) contentType = "video/x-flv";
        if (typeView.equalsIgnoreCase("m3u8")) contentType = "application/x-mpegURL";
        if (typeView.equalsIgnoreCase("ts")) contentType = "video/MP2T";
        if (typeView.equalsIgnoreCase("3gp")) contentType = "video/3gpp";
        if (typeView.equalsIgnoreCase("mov")) contentType = "video/quicktime";
        if (typeView.equalsIgnoreCase("avi")) contentType = "video/x-msvideo";
        if (typeView.equalsIgnoreCase("wmv")) contentType = "video/x-ms-wmv";
        if (typeView.equalsIgnoreCase("pdf")) contentType = "application/pdf";
        return contentType;
    }


}
