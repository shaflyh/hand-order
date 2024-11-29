package com.hand.demo.infra.constant;

/**
 * This class contains common constants for HTTP response codes, messages,
 * and keys used across the application.
 *
 * @author muhammad.shafly@hand-global.com
 * @since 2024-11-29 15:16
 */
public class ResponseConstant {

    /**
     * Common HTTP Response Codes
     */
    public static class Code {
        public static final int SUCCESS = 200;                // OK
        public static final int BAD_REQUEST = 400;           // Bad Request
        public static final int INTERNAL_SERVER_ERROR = 500; // Internal Server Error
    }

    /**
     * Common Response Messages
     */
    public static class Message {
        public static final String SUCCESS = "Operation completed successfully.";
        public static final String BAD_REQUEST = "Invalid request. Please check your input.";
        public static final String INTERNAL_SERVER_ERROR = "An unexpected error occurred on the server.";
    }

    /**
     * Common Response Keys
     */
    public static class Key {
        public static final String STATUS = "status";
        public static final String CODE = "code";
        public static final String MESSAGE = "message";
        public static final String DATA = "data";
    }
}
