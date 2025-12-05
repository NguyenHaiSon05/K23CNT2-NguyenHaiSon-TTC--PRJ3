package com.freshfruit.nhsutil;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.*;

public class FileUploadUtil {

    public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        } catch (IOException ioe) {
            throw new IOException("Không thể lưu file ảnh: " + fileName, ioe);
        }
    }
}
