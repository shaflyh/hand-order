package com.hand.demo.api.controller.v1;

import com.hand.demo.api.dto.WorkflowEventRequestDTO;
import com.hand.demo.app.service.InvCountHeaderService;
import com.hand.demo.config.SwaggerTags;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = SwaggerTags.INVOICE)
@RestController("InvoiceController.v1")
@RequestMapping("/v1/{organizationId}/invoices")
public class InvCountHeaderController {

    private final InvCountHeaderService invCountHeaderService;

    public InvCountHeaderController(InvCountHeaderService invCountHeaderService) {
        this.invCountHeaderService = invCountHeaderService;
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "Approval Callback")
    @PostMapping(path = "/approval-callback")
    public ResponseEntity<String> approvalCallback(@PathVariable("organizationId") Long tenantId,
                                       @RequestBody WorkflowEventRequestDTO workflowEventRequestDTO) {
        invCountHeaderService.approvalCallback(tenantId, workflowEventRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}