package com.hand.demo.api.dto;

import java.util.Date;

/**
 * @author muhammad.shafly@hand-global.com
 * @since 2024-11-28 13:09
 */
public class WorkflowEventRequestDTO {

    /**
     * Workflow ID
     */
    private Long workflowId;

    /**
     *  Business Key
     */
    private String businessKey;

    /**
     * Document Status
     */
    private String docStatus;

    /**
     * Approved Time
     */
    private Date approvedTime;

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public Date getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(Date approvedTime) {
        this.approvedTime = approvedTime;
    }
}
