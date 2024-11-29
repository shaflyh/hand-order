package com.hand.demo.api.dto;

import java.util.Map;

/**
 * @author muhammad.shafly@hand-global.com
 * @since 2024-11-29 09:10
 */
public class WorkflowRequestDTO {
    private String businessKey;
    private String flowKey;
    private String dimension;
    private String starter;
    private Map<String, Object> variableMap;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public Map<String, Object> getVariableMap() {
        return variableMap;
    }

    public void setVariableMap(Map<String, Object> variableMap) {
        this.variableMap = variableMap;
    }
}
