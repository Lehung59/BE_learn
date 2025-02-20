package com.example.rabiloo.controller;

import com.example.rabiloo.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class UploadController {

    public final StorageService storageService;

    @GetMapping("/{file_path}")
    public ResponseEntity<?> viewFile(@PathVariable(name = "file_path") String pathx) throws IOException {
        log.info("view path: {}", pathx);

        return storageService.viewFile(pathx);
    }



}