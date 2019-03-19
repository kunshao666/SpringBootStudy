package com.kunshao.springbootstudy.advice;

import com.kunshao.springbootstudy.exception.TestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandlerAdvice {
    @ExceptionHandler(value = TestException.class)
    public ResponseEntity<String> TestExceptionHandler(TestException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * 输入参数验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        BindingResult result = e.getBindingResult();
        if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            for(ObjectError error : errorList){
                message.append(error.getDefaultMessage());
                message.append(";");
            }
        }
        return new ResponseEntity<String>(message.toString(), HttpStatus.BAD_REQUEST);
    }
}
