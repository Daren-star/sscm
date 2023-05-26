package com.lsw.sscm.tool;

public enum ResultEnum {

        OK(200,"请求成功"),
        // 登录相关
        LOGIN_SUCCESS(200, "登录成功"),
        LOGIN_FAIL(401, "登录失败"),
        INVALID_CREDENTIALS(403, "无效的凭据"),

        // 数据操作相关
        OPERATION_SUCCESS(200, "操作成功"),
        OPERATION_FAIL(500, "操作失败"),
        INSUFFICIENT_PERMISSION(403, "权限不足"),
        BAD_REQUEST(400, "错误的请求");

        private final Integer code;
        private final String message;

        ResultEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }