package com.hand.demo.app.service.impl;

import com.hand.demo.api.dto.MessageDto;
import com.hand.demo.app.service.MessageService;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.Receiver;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageClient messageClient;

    public MessageServiceImpl(MessageClient messageClient) {
        this.messageClient = messageClient;
    }

    @Override
    public void sendNotification(Long tenantId, Long userId, String messages) {
        Receiver receiver = new Receiver();
        receiver.setUserId(userId);
        receiver.setTargetUserTenantId(tenantId);

        final String TEMPLATE_CODE = "Test-47833";
        final String LANGUAGE_CODE = "en_US";

//        for (MessageDto message : messages) {
//
//        }
        Map<String, String> args = new HashMap<>();
//            args.put("userName", message.getUserName());
//            args.put("employeeNumber", message.getEmployeeNumber());
//            args.put("email", message.getEmail());
//            args.put("date", message.getDate());
        args.put("msg", "This is a test message");


        Message message1 = messageClient.sendWebMessage(tenantId, TEMPLATE_CODE, LANGUAGE_CODE,
                Collections.singletonList(receiver),
                args);
    }

    @Override
    public void sendEmail(String context, String email, Long tenantId) {
        Receiver receiver = new Receiver();
        receiver.setEmail(email);
        receiver.setTargetUserTenantId(tenantId);

        final String SUBJECT = "HZERO Test from Code-47833";
        final String SERVER_CODE = "DEMO-47833";

        messageClient.sendCustomEmail(
                tenantId,
                SERVER_CODE,
                SUBJECT,
                context,
                Collections.singletonList(receiver),
                null,
                null,
                null
        );
    }

}