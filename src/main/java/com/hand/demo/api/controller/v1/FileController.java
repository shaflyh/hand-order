package com.hand.demo.api.controller.v1;

import com.hand.demo.api.dto.FileInfoDTO;
import com.hand.demo.app.service.FileService;
import com.hand.demo.config.SwaggerTags;
import feign.Response;
import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.file.dto.FileDTO;
import org.hzero.boot.file.dto.FileParamsDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = SwaggerTags.FILE)
@RestController("FileController.v1")
@RequestMapping("/v1/{organizationId}/file")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "Upload File")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@PathVariable("organizationId") Long organizationId,
                                             FileInfoDTO fileInfoDTO) {
        try {
            fileService.uploadFile(organizationId, fileInfoDTO);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "Get Page Files")
    @GetMapping("/getPages")
    public ResponseEntity<Page<FileDTO>> getPageFiles(
            @PathVariable Long organizationId,
            @RequestParam Integer page,
            @RequestParam Integer size,
            FileParamsDTO fileParams) {
        try {
            Page<FileDTO> filePage = fileService.getAllFiles(fileParams, page, size);
            return ResponseEntity.ok(filePage);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "Get Attachment Files")
    @GetMapping("/getAttachments")
    public ResponseEntity<List<FileDTO>> getAttachmentFiles(
            @PathVariable Long organizationId,
            @RequestParam String bucketName,
            @RequestParam String attachmentUUID) {
        try {
            List<FileDTO> fileList = fileService.getAttachmentFiles(organizationId, bucketName, attachmentUUID);
            return ResponseEntity.ok(fileList);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "Download File")
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Long organizationId,
                                                            @RequestParam String fileKey) {
        try {
            InputStreamResource resource = fileService.downloadFile(organizationId, fileKey);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileKey + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "Delete File")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(@PathVariable Long organizationId,
                                             @RequestParam String bucketName,
                                             @RequestParam String storageCode,
                                             @RequestParam List<String> attachmentUUIDs,
                                             @RequestParam List<String> urls) {
        try {
            fileService.deleteFile(organizationId, bucketName, storageCode, attachmentUUIDs, urls);
            return ResponseEntity.ok("File deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "Watermark File")
    @PostMapping("/watermark")
    public ResponseEntity<String> watermark(@PathVariable("organizationId") Long organizationId,
                                            @RequestParam("fileKey") String fileKey,
                                            @RequestParam("watermarkCode") String watermarkCode) {
        try {
            Response response = fileService.watermarkByKey(organizationId, fileKey, watermarkCode);
            return ResponseEntity.ok("Watermark file success!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }
}
