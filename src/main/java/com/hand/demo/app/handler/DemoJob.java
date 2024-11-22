package com.hand.demo.app.handler;

import com.hand.demo.app.service.MessageService;
import org.hzero.boot.scheduler.infra.annotation.JobHandler;
import org.hzero.boot.scheduler.infra.enums.ReturnT;
import org.hzero.boot.scheduler.infra.handler.IJobHandler;
import org.hzero.boot.scheduler.infra.tool.SchedulerTool;

import java.util.Map;

@JobHandler("DEMO_JOB")
public class DemoJob implements IJobHandler {

    private final MessageService messageService;

    public DemoJob(MessageService userRepository) {
        this.messageService = userRepository;
    }

    @Override
    public ReturnT execute(Map<String, String> map, SchedulerTool tool) {
        messageService.sendFeishuMessage(map.get("userId"));
        return ReturnT.SUCCESS;
    }
}