package com.fpmislata.movies.http_errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.exception.SQLStatmentException;
 
@ControllerAdvice
public class ApiExceptionHandler {
 
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest(Exception exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }
     
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            SQLStatmentException.class
    })
    @ResponseBody
    public ErrorMessage SQLException(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage("SQL exception", HttpStatus.BAD_REQUEST.value());
    }
 
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            DBConnectionException.class,
    })
    @ResponseBody
    public ErrorMessage DBException(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage("DB timeout exception", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }    
 
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public ErrorMessage exception(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage("Internal error", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}