package com.hand.demo.app.service;

import com.hand.demo.api.dto.MessageDto;

import java.util.List;

public interface MessageService {
    public void sendNotification(Long tenantId, Long userId, String messages);

    public void sendEmail(String context, String email, Long tenantId);
}
