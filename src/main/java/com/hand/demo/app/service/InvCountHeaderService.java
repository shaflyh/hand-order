package com.hand.demo.app.service;

import com.hand.demo.api.dto.WorkflowEventRequestDTO;

public interface InvCountHeaderService {

    void approvalCallback(Long organizationId, WorkflowEventRequestDTO workflowEventRequestDTO);
}
