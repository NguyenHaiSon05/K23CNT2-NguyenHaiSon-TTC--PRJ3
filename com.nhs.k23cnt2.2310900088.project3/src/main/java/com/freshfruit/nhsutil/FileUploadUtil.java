package com.freshfruit.nhsutil;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUploadUtil {

    public static String saveFile(String uploadDir, MultipartFile file) throws IOException {

        // ✅ Tạo thư mục nếu chưa tồn tại
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // ✅ Đặt tên file an toàn
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        File dest = new File(dir, fileName);

        file.transferTo(dest);

        return fileName;
    }
}
