package com.hand.demo.domain.repository;

import com.hand.demo.domain.entity.InvCountHeader;
import org.hzero.mybatis.base.BaseRepository;

public interface InvCountHeaderRepository extends BaseRepository<InvCountHeader> {
    int updateByCountHeaderKey(InvCountHeader invCountHeader);
}
