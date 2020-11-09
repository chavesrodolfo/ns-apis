package com.gituhub.chavesrodolfo.customer.model.representations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageResponse {

    private final String status;
    private final String message;

}
