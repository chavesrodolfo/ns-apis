package com.gituhub.chavesrodolfo.customer.exceptions;

import com.gituhub.chavesrodolfo.customer.model.representations.MessageResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    MessageResponse customerNotFoundHandler(CustomerNotFoundException e) {
        return new MessageResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(CustomerNotAcceptedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    MessageResponse customerNotAcceptedHandler(CustomerNotAcceptedException e) {
        return new MessageResponse(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(CustomerCreatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    MessageResponse customerCreatedHandler(CustomerCreatedException e) {
        return new MessageResponse(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }
}