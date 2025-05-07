package com.rra.vms.exceptions.handlers;


import com.rra.vms.exceptions.ResourceNotFoundException;
import com.rra.vms.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@AllArgsConstructor
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public void handleResourceNotFoundException(Exception e){

    }

    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFoundException(Exception e){

    }
}
