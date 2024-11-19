package com.hand.demo.infra.mapper;

import io.choerodon.mybatis.common.BaseMapper;
import com.hand.demo.domain.entity.Task;

import java.util.List;

/**
 * 任务表(Task)应用服务
 *
 * @author shaoqin.zhou@hand-china.com
 * @since 2024-10-15 14:51:30
 */
public interface TaskMapper extends BaseMapper<Task> {
    /**
     * 查询任务
     *
     * @param params 任务查询参数
     * @return Task
     */
    List<Task> selectTask(Task params);
}