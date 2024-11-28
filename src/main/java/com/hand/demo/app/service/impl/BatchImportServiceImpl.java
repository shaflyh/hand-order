package com.hand.demo.app.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.demo.domain.entity.Task;
import com.hand.demo.domain.repository.TaskRepository;
import org.hzero.boot.imported.app.service.BatchImportHandler;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Shafly
 * @since 2024-12-18
 */
@ImportService(templateCode = "DEMO-CLIENT-47833")
public class BatchImportServiceImpl extends BatchImportHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TaskRepository repository;

    @Override
    public Boolean doImport(List<String> data) {
        // 获取自定义参数
        Map<String, Object> args = getArgs();
        List<Task> taskList = new ArrayList<>();
        if(data == null || data.isEmpty()){
            return false;
        }
        data.forEach(task -> {
            try {
                taskList.add(objectMapper.readValue(task, Task.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        // do import
        repository.batchInsertSelective(taskList);
        System.out.println(data);
        return true;
    }

    @Override
    public int getSize(){
        return 500;
    }
}
