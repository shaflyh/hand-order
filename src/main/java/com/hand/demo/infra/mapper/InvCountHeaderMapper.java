package com.hand.demo.infra.mapper;

import com.hand.demo.domain.entity.InvCountHeader;
import io.choerodon.mybatis.common.BaseMapper;

public interface InvCountHeaderMapper extends BaseMapper<InvCountHeader> {

    int updateByCountHeaderKey(InvCountHeader invCountHeader);
}
