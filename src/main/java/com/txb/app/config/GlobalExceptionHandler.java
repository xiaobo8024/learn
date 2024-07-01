package com.txb.app.config;

import com.txb.app.exception.MyException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handleAllExceptions(Exception ex) {
//        // 记录日志，处理异常信息
//        return "An error occurred: "+ ex.getMessage();
//    }


    @ExceptionHandler(MyException.class)
    public Map<String,String> handleMyCustomException(MyException e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("code","500");
        response.put("msg",e.getMessage());
        response.put("data",null);
        // 处理异常，返回适当的响应
        return response;
    }
}
