package com.devptit.award_app.exception;

public enum ErrorCode {
    UNCATEGORY_EXCEPTION(404, "Path not found"),
    KEY_INVALID(400, "Invalid message key"),
    SUCCESS(200, "Success")
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
