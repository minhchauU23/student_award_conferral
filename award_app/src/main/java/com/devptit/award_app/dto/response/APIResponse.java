package com.devptit.award_app.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class APIResponse<T>{
    int code;
    String message;
    T result;
}
