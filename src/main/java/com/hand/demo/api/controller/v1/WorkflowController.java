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
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> start(
            @PathVariable("organizationId") Long tenantId,
            @RequestBody WorkflowRequestDTO workflowRequestDTO) {
        return ResponseEntity.ok(workflowService.startWorkflow(tenantId, workflowRequestDTO));
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "Withdraw Workflow")
    @PostMapping("/withdraw")
    public ResponseEntity<Map<String, Object>> withdraw(
            @PathVariable("organizationId") Long tenantId,
            @ModelAttribute WorkflowRequestDTO workflowRequestDTO) {
        return Results.success(workflowService.withdrawWorkflow(tenantId, workflowRequestDTO));
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "Approve History")
    @GetMapping("/approval-history")
    public ResponseEntity<List<RunTaskHistory>> getApproveHistory(
            @PathVariable("organizationId") Long tenantId,
            @ModelAttribute WorkflowRequestDTO workflowRequestDTO) {
        return Results.success(workflowService.getApproveHistory(tenantId, workflowRequestDTO));
    }
}
