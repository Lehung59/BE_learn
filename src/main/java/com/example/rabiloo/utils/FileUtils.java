package com.example.rabiloo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUtils {

    public static String formatFileName(MultipartFile file) {
        String formattedFilename = "";
        if (file != null && file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
            formattedFilename = file.getOriginalFilename()
                    .trim()
                    .replaceAll("[\\s/\\\\,]+", "_");

            // Tạo mã UUID
            String uuid = UUID.randomUUID().toString().substring(0, 8);

            // Chèn UUID vào trước phần mở rộng của file
            int lastDotIndex = formattedFilename.lastIndexOf(".");

            if (lastDotIndex != -1) {
                formattedFilename = formattedFilename.substring(0, lastDotIndex) + "_" + uuid + formattedFilename.substring(lastDotIndex);
            } else {
                formattedFilename += "_" + uuid;
            }
        }

        return formattedFilename;
    }

    public static void saveFile(MultipartFile file, String fileName, String uploadPath) throws IOException {
        Path directory = Paths.get(uploadPath);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory.toAbsolutePath());
        }

        Path filePath = directory.resolve(fileName);

        byte[] bytes = file.getBytes();
        Files.write(filePath, bytes);
    }
}
