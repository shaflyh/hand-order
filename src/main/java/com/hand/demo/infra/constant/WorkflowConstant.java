package com.hand.demo.infra.constant;

/**
 * This class contains constants for response codes and messages
 * used across the application.
 *
 * @author muhammad.shafly@hand-global.com
 * @since 2024-11-29 14:49
 */
public class WorkflowConstant {

    /**
     * Workflow Response Messages
     */
    public static class Message {
        public static final String WORKFLOW_STARTED = "Workflow started successfully.";
        public static final String WORKFLOW_FAILED = "Failed to start the workflow: ";
        public static final String WORKFLOW_WITHDRAWN = "Workflow withdrawn successfully.";
        public static final String WORKFLOW_APPROVAL_HISTORY_RETRIEVED = "Approval history retrieved successfully.";
    }

    /**
     * Workflow-Specific Status Codes (Optional)
     */
    public static class Code {
        public static final int WORKFLOW_STARTED = 210; // Custom code for "workflow started"
        public static final int WORKFLOW_FAILED = 510; // Custom code for "workflow failed"
    }
}