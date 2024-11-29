package com.hand.demo.app.service.impl;

import com.hand.demo.api.dto.WorkflowRequestDTO;
import com.hand.demo.app.service.WorkflowService;
import org.hzero.boot.workflow.WorkflowClient;
import org.hzero.boot.workflow.dto.RunTaskHistory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author muhammad.shafly@hand-global.com
 * @since 2024-11-29 08:36
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {

    private final WorkflowClient workflowClient;

    public WorkflowServiceImpl(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    @Override
    public void startWorkflow(Long tenantId, WorkflowRequestDTO workflowStartDTO) {

        String flowKey = workflowStartDTO.getFlowKey(); // "FLOW1861967301276975105"
        String businessKey = workflowStartDTO.getBusinessKey(); // "TEST-47833-012"
        String dimension = workflowStartDTO.getDimension(); // "EMPLOYEE"
        String starter = workflowStartDTO.getStarter(); // "47833"
        Long paymentAmount = workflowStartDTO.getPaymentAmount();

        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put("paymentAmount", paymentAmount);

        workflowClient.startInstanceByFlowKey(tenantId, flowKey, businessKey, dimension, starter, variableMap);
    }

    @Override
    public void withdrawWorkflow(Long tenantId, WorkflowRequestDTO workflowStartDTO) {
        workflowClient.flowWithdrawFlowKey(tenantId, workflowStartDTO.getFlowKey(), workflowStartDTO.getBusinessKey());
    }

    @Override
    public List<RunTaskHistory> getApproveHistory(Long tenantId, WorkflowRequestDTO workflowStartDTO) {
        return workflowClient.approveHistoryByFlowKey(tenantId, workflowStartDTO.getFlowKey(), workflowStartDTO.getBusinessKey());
    }
}
