package com.gituhub.chavesrodolfo.campaign.exceptions;

import com.gituhub.chavesrodolfo.campaign.model.representations.MessageResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(CampaignNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    MessageResponse campaignNotFoundHandler(CampaignNotFoundException e) {
        return new MessageResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(CampaignNotAcceptedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    MessageResponse campaignNotAcceptedHandler(CampaignNotAcceptedException e) {
        return new MessageResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage());
    }
}