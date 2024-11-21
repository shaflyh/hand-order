package com.hand.demo.api.controller.v1;

import com.hand.demo.api.dto.MessageDto;
import com.hand.demo.app.service.MessageService;
import com.hand.demo.config.SwaggerTags;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = SwaggerTags.MESSAGE)
@RestController("MessageController.v1")
@RequestMapping("/v1/{organizationId}/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "Send Notification")
    @PostMapping("/notification")
    public ResponseEntity<?> sendNotifications(
            @PathVariable("organizationId") Long tenantId,
            @RequestParam(name = "receiver") Long receiver,
            @RequestBody String messages) {
        messageService.sendNotification(tenantId, receiver, messages);
        return ResponseEntity.status(HttpStatus.OK).body(
                "success"
        );
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "Send Email")
    @PostMapping(
            path = "/email"
    )
    public ResponseEntity<?> sendEmail(
            @PathVariable("organizationId") Long tenantId,
            @RequestParam(name = "email_receiver")String emailReceiver
            ,@RequestBody String context) {
        messageService.sendEmail(context, emailReceiver, tenantId);
        return ResponseEntity.status(HttpStatus.OK).body(
                "success"
        );
    }
}