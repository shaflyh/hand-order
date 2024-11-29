package com.hand.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel("Invoice Count Header")
@ModifyAudit
@VersionAudit
@Table(name = "todo_inv_count_header")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvCountHeader {

    public static final String FIELD_COUNT_HEADER_ID = "countHeaderId";
    public static final String FIELD_TENANT_ID = "tenantId";
    public static final String FIELD_COUNT_NUMBER = "countNumber";
    public static final String FIELD_COUNT_STATUS = "countStatus";
    public static final String FIELD_COUNT_TYPE = "countType";
    public static final String FIELD_COUNT_MODE = "countMode";
    public static final String FIELD_COUNTOR_IDS = "countorIds";
    public static final String FIELD_SUPERVISOR_IDS = "supervisorIds";
    public static final String FIELD_WORKFLOW_ID = "workflowId";
    public static final String FIELD_APPROVED_TIME = "approvedTime";
    public static final String FIELD_REMARK = "remark";
    public static final String FIELD_OBJECT_VERSION_NUMBER = "objectVersionNumber";
    public static final String FIELD_CREATION_DATE = "creationDate";
    public static final String FIELD_CREATED_BY = "createdBy";
    public static final String FIELD_LAST_UPDATED_BY = "lastUpdatedBy";
    public static final String FIELD_LAST_UPDATE_DATE = "lastUpdateDate";

    @Id
    @GeneratedValue
    @ApiModelProperty("Count Header ID")
    private Long countHeaderId;

    @NotNull(message = "Tenant ID cannot be null")
    @ApiModelProperty("Tenant ID")
    private Long tenantId;

    @ApiModelProperty("Count Number")
    private String countNumber;

    @ApiModelProperty("Count Status")
    private String countStatus;

    @ApiModelProperty("Count Type")
    private String countType;

    @ApiModelProperty("Count Mode")
    private String countMode;

    @ApiModelProperty("Countor IDs (comma-separated)")
    private String countorIds;

    @ApiModelProperty("Supervisor IDs (comma-separated)")
    private String supervisorIds;

    @ApiModelProperty("Workflow ID")
    private Long workflowId;

    @ApiModelProperty("Approved Time")
    private Date approvedTime;

    @Length(max = 500)
    @ApiModelProperty("Remark")
    private String remark;

    @ApiModelProperty("Object Version Number")
    private Long objectVersionNumber;

    @ApiModelProperty("Creation Date")
    private Date creationDate;

    @ApiModelProperty("Created By")
    private Long createdBy;

    @ApiModelProperty("Last Updated By")
    private Long lastUpdatedBy;

    @ApiModelProperty("Last Update Date")
    private Date lastUpdateDate;

    @Transient
    @ApiModelProperty("Additional Field Example")
    private String additionalField;

    /**
     * Generate unique identifier for count number.
     */
    public void generateCountNumber() {
        this.countNumber = "CNT-" + System.currentTimeMillis();
    }

    // Getters and Setters

    public Long getCountHeaderId() {
        return countHeaderId;
    }

    public void setCountHeaderId(Long countHeaderId) {
        this.countHeaderId = countHeaderId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(String countNumber) {
        this.countNumber = countNumber;
    }

    public String getCountStatus() {
        return countStatus;
    }

    public void setCountStatus(String countStatus) {
        this.countStatus = countStatus;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public String getCountMode() {
        return countMode;
    }

    public void setCountMode(String countMode) {
        this.countMode = countMode;
    }

    public String getCountorIds() {
        return countorIds;
    }

    public void setCountorIds(String countorIds) {
        this.countorIds = countorIds;
    }

    public String getSupervisorIds() {
        return supervisorIds;
    }

    public void setSupervisorIds(String supervisorIds) {
        this.supervisorIds = supervisorIds;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public Date getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(Date approvedTime) {
        this.approvedTime = approvedTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Long objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getAdditionalField() {
        return additionalField;
    }

    public void setAdditionalField(String additionalField) {
        this.additionalField = additionalField;
    }
}
