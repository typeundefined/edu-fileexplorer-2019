package ru.amm.fileexplorer.server.entity;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    public void upload(MultipartFile file);
}
