package com.hand.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger Api 描述配置
 */
@Configuration
public class SwaggerTags {

    public static final String EXAMPLE = "Example";
    public static final String USER = "User";
    public static final String TASK = "Task";
    public static final String MESSAGE = "Message";
    public static final String FILE = "File";
    public static final String INVOICE = "Invoice";
    public static final String WORKFLOW = "Workflow";

    @Autowired
    public SwaggerTags(Docket docket) {
        docket.tags(
                new Tag(EXAMPLE, "EXAMPLE 案例"),
                new Tag(USER, "USER"),
                new Tag(TASK, "TASK"),
                new Tag(MESSAGE, "MESSAGE"),
                new Tag(FILE, "FILE"),
                new Tag(INVOICE, "Invoice"),
                new Tag(WORKFLOW, "Workflow")
        );
    }
}
