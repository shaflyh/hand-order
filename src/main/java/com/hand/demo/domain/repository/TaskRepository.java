package com.hand.demo.domain.repository;

import java.util.List;

import com.hand.demo.domain.entity.Task;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.BaseRepository;

/**
 * 任务资源库
 */
public interface TaskRepository extends BaseRepository<Task> {
    /**
     * 分页查询任务
     *
     * @param task        Task
     * @param pageRequest 分页参数
     * @param employeeId  员工ID
     * @param taskNumber  任务编号
     * @return Page<Task>
     * <p>
     * /
     * Page<Task> pageTask(Task task, PageRequest pageRequest);
     * /*
     * <p>
     * 根据员工ID查询任务
     * @return List<Task>
     * <p>
     * /
     * List<Task> selectByEmployeeId(Long employeeId);
     * /*
     * <p>
     * 根据任务编号查询任务详细(包含员工姓名)
     * @return Task
     */
    Task selectDetailByTaskNumber(String taskNumber);
    Page<Task> pageTask(Task task, PageRequest pageRequest);
    List<Task> selectByEmployeeId(Long employeeId);
}