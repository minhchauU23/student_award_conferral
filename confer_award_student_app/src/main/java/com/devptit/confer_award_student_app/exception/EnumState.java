package com.devptit.confer_award_student_app.exception;

public enum EnumState {
    CREATED("CREATED", "SUCCESS"),
    OUTSIDE_EVENT_TIME("outside of event time", "outside of event time"),
    END_TASK("END_TASK", "SUCCESS"),
    KEY_INVALID("key invalid", "key invalid"),

    GET_EVENT_DESCRIPTION_SUCCESS("GET_EVENT_DESCRIPTION", "SUCCESS"),
    GET_EVENT_DESCRIPTION_FAILED("GET_EVENT_DESCRIPTION", "FAILED"),

    GET_STUDENT_TRANSCRIPT_SUCCESS("GET_STUDENT_TRANSCRIPT", "SUCCESS"),
    GET_STUDENT_TRANSCRIPT_FAILED("GET_STUDENT_TRANSCRIPT", "FAILED"),

    GET_AWARD_RULE_SUCCESS("GET_AWARD_RULE", "SUCCESS"),
    GET_AWARD_RULE_FAILED("GET_AWARD_RULE", "FAILED"),
    VERIFY_STUDENT_TRANSCRIPT_SUCCESS("VERIFY_STUDENT_TRANSCRIPT", "SUCCESS"),
    VERIFY_STUDENT_TRANSCRIPT_FAILED("VERIFY_STUDENT_TRANSCRIPT", "FAILED"),

    VERIFY_EVENT_SUCCESS("VERIFY_EVENT", "SUCCESS"),
    VERIFY_EVENT_FAILED("VERIFY_EVENT", "FAILED"),

    UPDATE_AWARD_HISTORY_SUCCESS("UPDATE_AWARD_HISTORY","SUCCESS" ),
    UPDATE_AWARD_HISTORY_FAILED("UPDATE_AWARD_HISTORY","FAILED" ),

    UPDATE_STUDENT_TRANSCRIPT_SUCCESS("UPDATE_STUDENT_TRANSCRIPT", "SUCCESS"),

    UPDATE_STUDENT_TRANSCRIPT_FAILED("UPDATE_STUDENT_TRANSCRIPT", "FAILED"),
    SEND_NOTIFICATION_SUCCESS("SEND_NOTIFICATION_SUCCESS", "SUCCESS"),
    SEND_NOTIFICATION_FAILED("SEND_NOTIFICATION_FAILED", "FAILED")
    ;

    private String name;
    private String state;

    EnumState(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
