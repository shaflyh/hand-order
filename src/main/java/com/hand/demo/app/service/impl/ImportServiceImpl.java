package com.hand.demo.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.demo.domain.entity.Task;
import com.hand.demo.domain.repository.TaskRepository;
import org.hzero.boot.imported.app.service.ImportHandler;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@ImportService(templateCode = "DEMO-CLIENT-47833")
public class ImportServiceImpl extends ImportHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TaskRepository taskRepository;

    public ImportServiceImpl(ObjectMapper objectMapper, TaskRepository taskRepository) {
        this.objectMapper = objectMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public Boolean doImport(String data) {
        try {
            Task task = objectMapper.readValue(data, Task.class);
            taskRepository.insertSelective(task);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}