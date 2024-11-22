package com.hand.demo.app.service.impl;

import com.hand.demo.app.service.MessageService;
import com.hand.demo.app.service.UserService;
import com.hand.demo.domain.entity.User;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.FlyBookMsgType;
import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.Receiver;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageClient messageClient;
    private final UserService userService;

    public MessageServiceImpl(MessageClient messageClient, UserService userService) {
        this.messageClient = messageClient;
        this.userService = userService;
    }

    @Override
    public void sendNotification(Long tenantId, Long userId, String messages) {
        Receiver receiver = new Receiver();
        receiver.setUserId(userId);
        receiver.setTargetUserTenantId(tenantId);

        final String TEMPLATE_CODE = "Test-47833";
        final String LANGUAGE_CODE = "en_US";

        Map<String, String> args = new HashMap<>();
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

    @Override
    public void sendFeishuMessage(String userId) {
        long tenantId = 0L;
        String serverCode = "FEIYU";
        String messageTemplateCode = "TEST-FEISHU-47833";
        FlyBookMsgType msgType = FlyBookMsgType.TEXT;
        String lang = "en_US";
        Map<String, String> receiverMap = new HashMap<String, String>();
        receiverMap.put("email", "shaoqin.zhou@hand-china.com");

        System.out.println("\n\n\nTrying to send the message...");
        User user = userService.getUserByKey(Long.parseLong(userId));
        String name = user.getEmployeeName();
        String number = user.getEmployeeNumber();
        String email = user.getEmail();
        System.out.println(name);
        System.out.println(number);
        System.out.println(email);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", name);
        param.put("number", number);
        param.put("email", email);

        messageClient.sendFlyBook(tenantId, serverCode, messageTemplateCode, msgType, lang, Collections.singletonList(receiverMap), param);

        System.out.println("MESSAGE SENT!!!!!!");
    }
}