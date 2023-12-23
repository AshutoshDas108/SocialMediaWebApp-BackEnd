package com.socials.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    /*
    Postman gives large messages for exceptions
     which are not readable, so we
      will extract only those info
        that are needed
        */

    private String message;

    private String error;

    private LocalDateTime timeStamp;
}

