package com.hand.demo.app.service.impl;

import com.hand.demo.api.dto.WorkflowRequestDTO;
import com.hand.demo.app.service.WorkflowService;
import com.hand.demo.infra.constant.ResponseConstant;
import com.hand.demo.infra.constant.WorkflowConstant;
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
    public Map<String, Object> startWorkflow(Long tenantId, WorkflowRequestDTO requestDTO) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        try {
            String flowKey = requestDTO.getFlowKey(); // "FLOW1861967301276975105"
            String businessKey = requestDTO.getBusinessKey(); // "TEST-47833-012"
            String dimension = requestDTO.getDimension(); // "EMPLOYEE"
            String starter = requestDTO.getStarter(); // "47833"

            Map<String, Object> variableMap = requestDTO.getVariableMap();
            System.out.println(variableMap);
            // Call workflow client to start the workflow
            workflowClient.startInstanceByFlowKey(tenantId, flowKey, businessKey, dimension, starter, variableMap);

            // Create response data
            data.put("flowKey", flowKey);
            data.put("businessKey", businessKey);
            data.put("dimension", dimension);
            data.put("starter", starter);

            // Build the response
            response.put(ResponseConstant.Key.STATUS, ResponseConstant.Message.SUCCESS);
            response.put(ResponseConstant.Key.CODE, ResponseConstant.Code.SUCCESS);
            response.put(ResponseConstant.Key.MESSAGE, WorkflowConstant.Message.WORKFLOW_STARTED);
            response.put(ResponseConstant.Key.DATA, data);
        } catch (Exception e) {
            response.put(ResponseConstant.Key.STATUS, ResponseConstant.Message.INTERNAL_SERVER_ERROR);
            response.put(ResponseConstant.Key.CODE, ResponseConstant.Code.INTERNAL_SERVER_ERROR);
            response.put(ResponseConstant.Key.MESSAGE, WorkflowConstant.Message.WORKFLOW_FAILED + e.getMessage());
            response.put(ResponseConstant.Key.DATA, data);
        }
        return response;
    }

    @Override
    public Map<String, Object> withdrawWorkflow(Long tenantId, WorkflowRequestDTO requestDTO) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        try {
            workflowClient.flowWithdrawFlowKey(tenantId, requestDTO.getFlowKey(), requestDTO.getBusinessKey());
            // Create response data
            data.put("flowKey", requestDTO.getFlowKey());
            data.put("businessKey", requestDTO.getBusinessKey());

            // Build the response
            response.put(ResponseConstant.Key.STATUS, ResponseConstant.Message.SUCCESS);
            response.put(ResponseConstant.Key.CODE, ResponseConstant.Code.SUCCESS);
            response.put(ResponseConstant.Key.MESSAGE, WorkflowConstant.Message.WORKFLOW_WITHDRAWN);
            response.put(ResponseConstant.Key.DATA, data);
        } catch (Exception e) {
            response.put(ResponseConstant.Key.STATUS, ResponseConstant.Message.INTERNAL_SERVER_ERROR);
            response.put(ResponseConstant.Key.CODE, ResponseConstant.Code.INTERNAL_SERVER_ERROR);
            response.put(ResponseConstant.Key.MESSAGE, WorkflowConstant.Message.WORKFLOW_FAILED + e.getMessage());
            response.put(ResponseConstant.Key.DATA, data);
        }
        return response;
    }

    @Override
    public List<RunTaskHistory> getApproveHistory(Long tenantId, WorkflowRequestDTO requestDTO) {
        return workflowClient.approveHistoryByFlowKey(tenantId, requestDTO.getFlowKey(), requestDTO.getBusinessKey());
    }
}
