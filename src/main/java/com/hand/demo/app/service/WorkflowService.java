package com.hand.demo.app.service;

import com.hand.demo.api.dto.WorkflowRequestDTO;
import org.hzero.boot.workflow.dto.RunTaskHistory;

import java.util.List;
import java.util.Map;

public interface WorkflowService {

    Map<String, Object> startWorkflow(Long tenantId, WorkflowRequestDTO workflowRequestDTO);

    Map<String, Object> withdrawWorkflow(Long tenantId, WorkflowRequestDTO workflowRequestDTO);

    List<RunTaskHistory> getApproveHistory(Long tenantId, WorkflowRequestDTO workflowRequestDTO);
}
