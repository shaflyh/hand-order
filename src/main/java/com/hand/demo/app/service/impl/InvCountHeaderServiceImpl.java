package com.hand.demo.app.service.impl;

import com.hand.demo.domain.entity.InvCountHeader;
import com.hand.demo.api.dto.WorkflowEventRequestDTO;
import com.hand.demo.app.service.InvCountHeaderService;
import com.hand.demo.domain.repository.InvCountHeaderRepository;
import org.springframework.stereotype.Service;

/**
 * @author muhammad.shafly@hand-global.com
 * @since 2024-11-28 13:44
 */
@Service
public class InvCountHeaderServiceImpl implements InvCountHeaderService {

    private final InvCountHeaderRepository repository;

    public InvCountHeaderServiceImpl(InvCountHeaderRepository invCountHeaderRepository) {
        this.repository = invCountHeaderRepository;
    }

    @Override
    public void approvalCallback(Long organizationId, WorkflowEventRequestDTO workflowEventRequestDTO) {
        InvCountHeader invCountHeader = new InvCountHeader();
        invCountHeader.setTenantId(organizationId);
        invCountHeader.setCountNumber(workflowEventRequestDTO.getBusinessKey());
        invCountHeader.setCountStatus(workflowEventRequestDTO.getDocStatus());
        invCountHeader.setWorkflowId(workflowEventRequestDTO.getWorkflowId());
        invCountHeader.setApprovedTime(workflowEventRequestDTO.getApprovedTime());

        InvCountHeader existingInvCountHeader = new InvCountHeader();


        // Retrieve the matching record (first match)
        InvCountHeader existingRecord = repository.selectOne(new InvCountHeader() {{
            setCountNumber(workflowEventRequestDTO.getBusinessKey());
            setTenantId(organizationId);
        }});
        System.out.println(existingRecord);

        if (existingRecord != null) {
            System.out.println(existingRecord.getCountStatus());
            System.out.println(existingRecord.getCountHeaderId());
            // Update existing record using its primary key
            invCountHeader.setCountHeaderId(existingRecord.getCountHeaderId()); // Set the primary key for the update
            System.out.println(invCountHeader.getCountStatus());
            System.out.println(invCountHeader.getCountHeaderId());
            repository.updateByCountHeaderKey(invCountHeader);
        } else {
            // Insert new record
            repository.insert(invCountHeader);
        }
    }
}
