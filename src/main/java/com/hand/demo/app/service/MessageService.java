package com.hand.demo.app.service;

public interface MessageService {
    void sendNotification(Long tenantId, Long userId, String messages);

    void sendEmail(String context, String email, Long tenantId);

    void sendFeishuMessage(String userId);
}
