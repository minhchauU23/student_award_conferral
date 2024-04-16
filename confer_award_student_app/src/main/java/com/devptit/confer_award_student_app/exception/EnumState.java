package com.devptit.confer_award_student_app.exception;

public enum EnumState {
    CREATED("created", "task is created"),
    OUTSIDE_EVENT_TIME("outside of event time", "outside of event time"),
    END_TASK("end task", "Task is stopped"),
    KEY_INVALID("key invalid", "key invalid"),
    GETTING_EVENT_DESCRIPTION("getting event description", "getting event description"),
    GET_EVENT_DESCRIPTION_SUCCESS("get event description success", "get event description success"),
    GETTING_STUDENT_TRANSCRIPT("getting student transcript", "getting student transcript"),
    GET_STUDENT_TRANSCRIPT_SUCCESS("get student transcript success", "get student transcript success"),
    GETTING_AWARD_RULE("getting award rules", "getting award rules"),
    GET_AWARD_RULE_SUCCESS("get award rules success", "get award rules success"),
    STUDENT_NOT_ELIGIBLE("students are not eligible", "students are not eligible"),

    VERIFY_STUDENT_TRANSCRIPT_SUCCESS("verify student transcript success", "verify student transcript success"),
    VERIFY_EVENT_SUCCESS("verify event success", "verify event success"),
    UPDATING_AWARD_HISTORY("updating award history","updating award history" ),
    UPDATING_STUDENT_TRANSCRIPT("updating student transcript", "updating student transcript"),
    UPDATE_AWARD_HISTORY_SUCCESS("update award history success","updating award history success" ),
    UPDATE_STUDENT_TRANSCRIPT_SUCCESS("update student transcript success", "updating student transcript success")


    ;

    private String state;
    private String description;

    EnumState(String state, String description) {
        this.state = state;
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
