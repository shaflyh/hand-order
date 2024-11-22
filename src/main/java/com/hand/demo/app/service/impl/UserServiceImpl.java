package com.hand.demo.app.service.impl;

import com.hand.demo.domain.entity.Task;
import com.hand.demo.domain.entity.User;
import com.hand.demo.domain.repository.TaskRepository;
import com.hand.demo.domain.repository.UserService;
import io.choerodon.core.exception.CommonException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户应用服务实现
 */
@Service
public class UserServiceImpl implements com.hand.demo.app.service.UserService {
    private final TaskRepository taskRepository;
    private final UserService userRepository;

    public UserServiceImpl(TaskRepository taskRepository, UserService userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User create(User user) {
        userRepository.insert(user);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long userId) {
        User exist = userRepository.selectByPrimaryKey(userId);
        if (exist == null) {
            throw new CommonException("htdo.warn.user.notFound");
        }
        // 删除用户
        userRepository.deleteByPrimaryKey(userId);
        // 删除与用户关联的任务
        List<Task> tasks = taskRepository.selectByEmployeeId(userId);
        if (CollectionUtils.isNotEmpty(tasks)) {
            taskRepository.batchDelete(tasks);
        }
    }

    @Override
    public User getUserByKey(Long userId) {
        return userRepository.selectByPrimaryKey(userId);
    }
}