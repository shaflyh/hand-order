package com.hand.demo.app.service.impl;

import com.hand.demo.api.dto.FileInfoDTO;
import com.hand.demo.app.service.FileService;
import feign.Response;
import io.choerodon.core.domain.Page;
import org.hzero.boot.file.FileClient;
import org.hzero.boot.file.dto.FileDTO;
import org.hzero.boot.file.dto.FileParamsDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final FileClient fileClient;

    public FileServiceImpl(FileClient fileClient) {
        this.fileClient = fileClient;
    }

    @Override
    public void uploadFile(Long organizationId, FileInfoDTO dto) {
        try {
            // Extract file data
            String fileNameUpload = (dto.getFileName() != null && !dto.getFileName().isEmpty()) ? dto.getFileName() : dto.getFile().getOriginalFilename();
            String fileType = dto.getFile().getContentType();
            byte[] fileData = dto.getFile().getBytes();

            String attachmentUUID = UUID.randomUUID().toString().replace("-", "");

            fileClient.uploadAttachment(organizationId, dto.getBucketName(), dto.getDirectory(), attachmentUUID, fileNameUpload, fileType, dto.getStorageCode(), fileData);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage(), e);
        }
    }

    @Override
    public Page<FileDTO> getAllFiles(FileParamsDTO fileParams, Integer page, Integer size) {
        try {
            return fileClient.pageFiles(fileParams, page, size);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve page files: " + e.getMessage(), e);
        }
    }

    @Override
    public List<FileDTO> getAttachmentFiles(Long organizationId, String bucketName, String attachmentUUID) {
        try {
            return fileClient.getAttachmentFiles(organizationId, bucketName, attachmentUUID);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve attachment files: " + e.getMessage(), e);
        }
    }

    @Override
    public InputStreamResource downloadFile(Long organizationId, String fileKey) {
        try {
            InputStream fileStream = fileClient.downloadFile(organizationId, fileKey);
            return new InputStreamResource(fileStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to download file: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteFile(Long organizationId, String bucketName, String storageCode, List<String> attachmentUUIDs, List<String> urls) {
        try {
            if (attachmentUUIDs != null && !attachmentUUIDs.isEmpty()) {
                fileClient.deleteFile(organizationId, bucketName, attachmentUUIDs, storageCode);
            }
            if (urls != null && !urls.isEmpty()) {
                fileClient.deleteFileByUrl(organizationId, bucketName, storageCode, urls);
            }
            if ((attachmentUUIDs == null || attachmentUUIDs.isEmpty()) && (urls == null || urls.isEmpty())) {
                throw new IllegalArgumentException("Either attachmentUUIDs or urls must be provided.");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response watermarkByKey(Long organizationId, String fileKey, String watermarkCode) {
        try {
            return fileClient.watermarkByKey(organizationId, fileKey, watermarkCode, "THIS IS WATERMARK");
        } catch (Exception e) {
            throw new RuntimeException("Failed to watermark file: " + e.getMessage(), e);
        }
    }
}
