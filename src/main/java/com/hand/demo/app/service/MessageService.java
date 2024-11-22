package com.hand.demo.app.service;

public interface MessageService {
    public void sendNotification(Long tenantId, Long userId, String messages);

    public void sendEmail(String context, String email, Long tenantId);

    public void sendFeishuMessage(String userId);
}
