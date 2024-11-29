package com.hand.demo.api.controller.v1;

import com.hand.demo.api.dto.WorkflowRequestDTO;
import com.hand.demo.app.service.WorkflowService;
import com.hand.demo.config.SwaggerTags;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.workflow.dto.RunTaskHistory;
import org.hzero.core.util.Results;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author muhammad.shafly@hand-global.com
 * @since 2024-11-29 08:56
 */
@Api(tags = SwaggerTags.WORKFLOW)
@RestController("workflowController.v1")
@RequestMapping("/v1/{organizationId}/workflows")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "Start Workflow")
    @PostMapping("/start")
    public ResponseEntity<String> start(@PathVariable("organizationId") Long tenantId, WorkflowRequestDTO workflowStartDTO) {
        workflowService.startWorkflow(tenantId, workflowStartDTO);
        return Results.success("Workflow started");
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "Withdraw Workflow")
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable("organizationId") Long tenantId, WorkflowRequestDTO workflowStartDTO) {
        workflowService.withdrawWorkflow(tenantId, workflowStartDTO);
        return Results.success("Workflow withdrawn");
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "Approve History")
    @GetMapping("/approval-history")
    public ResponseEntity<List<RunTaskHistory>> getApproveHistory(@PathVariable("organizationId") Long tenantId, WorkflowRequestDTO workflowStartDTO) {
        workflowService.getApproveHistory(tenantId, workflowStartDTO);
        return Results.success(workflowService.getApproveHistory(tenantId, workflowStartDTO));
    }
}
