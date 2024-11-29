package com.hand.demo.app.service;

import com.hand.demo.api.dto.WorkflowRequestDTO;
import org.hzero.boot.workflow.dto.RunTaskHistory;

import java.util.List;

public interface WorkflowService {

    void startWorkflow(Long tenantId, WorkflowRequestDTO workflowStartDTO);

    void withdrawWorkflow(Long tenantId, WorkflowRequestDTO workflowStartDTO);

    List<RunTaskHistory> getApproveHistory(Long tenantId, WorkflowRequestDTO workflowStartDTO);
}
