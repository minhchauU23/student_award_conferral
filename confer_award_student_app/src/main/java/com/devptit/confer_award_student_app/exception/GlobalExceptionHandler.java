package com.devptit.confer_award_student_app.exception;

import com.devptit.confer_award_student_app.dto.response.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;


@ControllerAdvice
public class GlobalExceptionHandler  {



    @ExceptionHandler(value = ConnectException.class)
    ResponseEntity<APIResponse> connectExceptionHandler(Exception exception){
        APIResponse apiRespone = new APIResponse();
        apiRespone.setCode(ErrorCode.REFUSED_CONNECT_EXCEPTION.getCode());
        apiRespone.setMessage(ErrorCode.REFUSED_CONNECT_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(apiRespone);
    }

    @ExceptionHandler( value = AppException.class)
    ResponseEntity<APIResponse> appHandlingException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        APIResponse apiRespone = new APIResponse();
        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiRespone);
    }

//    @ExceptionHandler(value = Exception.class)
//    ResponseEntity<APIResponse> unCategoryHandlingException(Exception exception){
//        APIResponse apiRespone = new APIResponse();
//        apiRespone.setCode(ErrorCode.UNCATEGORY_EXCEPTION.getCode());
//        apiRespone.setMessage(ErrorCode.UNCATEGORY_EXCEPTION.getMessage());
//        return ResponseEntity.badRequest().body(apiRespone);
//    }


}
