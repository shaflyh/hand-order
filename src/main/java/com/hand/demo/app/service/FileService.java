package com.hand.demo.app.service;

import com.hand.demo.api.dto.FileInfoDTO;
import feign.Response;
import io.choerodon.core.domain.Page;
import org.hzero.boot.file.dto.FileDTO;
import org.hzero.boot.file.dto.FileParamsDTO;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

public interface FileService {

    void uploadFile(Long organizationId, FileInfoDTO dto);

    Page<FileDTO> getAllFiles(FileParamsDTO fileParams, Integer page, Integer size);

    List<FileDTO> getAttachmentFiles(Long organizationId, String bucketName, String attachmentUUID);

    InputStreamResource downloadFile(Long organizationId, String fileKey);

    void deleteFile(Long organizationId, String bucketName, String storageCode, List<String> attachmentUUIDs, List<String> urls);

    Response watermarkByKey(Long organizationId, String fileKey, String watermarkCode);
}
