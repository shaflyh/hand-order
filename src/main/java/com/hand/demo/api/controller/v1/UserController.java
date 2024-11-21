package com.hand.demo.api.controller.v1;

import com.hand.demo.app.service.UserService;
import com.hand.demo.config.SwaggerTags;
import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.Receiver;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.hzero.mybatis.helper.SecurityTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hand.demo.domain.entity.User;
import com.hand.demo.domain.repository.UserRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户接口
 */
@Api(tags = SwaggerTags.USER)
@RestController("userController.v1")
@RequestMapping("/v1/users")
public class UserController extends BaseController {
    private final UserService userService;
    private final UserRepository userRepository;
    MessageClient messageClient;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

//    @Permission(level = ResourceLevel.SITE)
//    @ApiOperation(value = "Send Email")
//    @GetMapping("/messages/")
    public ResponseEntity<String> sendEmail() {
        Long tenantId = 0L;
        System.out.println("HELOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

        Receiver receiver = new Receiver()
                .setEmail("muhammad.shafly@hand-global.com") // Required for email sending
                .setUserId(12345L)                           // Assuming this is required or available
                .setTargetUserTenantId(tenantId);                  // Tenant ID might be needed for multi-tenant setups

        List<Receiver> receiverList = Arrays.asList(receiver);

        Map<String, String> args = new HashMap<>();
        args.put("emplNumber", "47833");
        args.put("name", "Muhammad Shafly Hamzah");

        Message message = messageClient.sendEmail(
                tenantId,                            // tenantId
                "DEMO-47833",                  // Replace with your serverCode
                "DEMO-47833",                  // messageTemplateCode from payload
                receiverList,                  // Recipients
                args                           // Template arguments
        );
        return Results.success("Success!");
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "分页查询用户")
    @GetMapping("/test")
    public ResponseEntity<Page<User>> test(User user, PageRequest pageRequest) {
        return Results.success(userRepository.pageAndSort(pageRequest, user));
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "分页查询用户")
    @GetMapping
    public ResponseEntity<Page<User>> list(User user, PageRequest pageRequest) {
        return Results.success(userRepository.pageAndSort(pageRequest, user));
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "创建 todo 用户")
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        // 简单数据校验
        this.validObject(user);
        // 创建用户
        return Results.success(userService.create(user));
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "删除 todo 用户")
    @DeleteMapping
    public ResponseEntity<User> delete(@RequestBody User user) {
        // 数据防篡改校验
        SecurityTokenHelper.validToken(user);
        // 删除用户
        userService.delete(user.getId());
        return Results.success();
    }
}