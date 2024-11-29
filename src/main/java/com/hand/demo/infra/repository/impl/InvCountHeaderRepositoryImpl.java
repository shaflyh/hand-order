package com.hand.demo.infra.repository.impl;

import com.hand.demo.domain.entity.InvCountHeader;
import com.hand.demo.domain.repository.InvCountHeaderRepository;
import com.hand.demo.infra.mapper.InvCountHeaderMapper;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Component;

/**
 * @author muhammad.shafly@hand-global.com
 * @since 2024-11-28 14:19
 */
@Component
public class InvCountHeaderRepositoryImpl extends BaseRepositoryImpl<InvCountHeader> implements InvCountHeaderRepository {

    private final InvCountHeaderMapper mapper;

    public InvCountHeaderRepositoryImpl(InvCountHeaderMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int updateByCountHeaderKey(InvCountHeader invCountHeader){
        return mapper.updateByCountHeaderKey(invCountHeader);
    }

}
