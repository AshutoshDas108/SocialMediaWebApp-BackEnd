package com.socials.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ue, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(ue.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now());


        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StoryException.class)
    public ResponseEntity<ErrorDetails> storyExceptionHandler(StoryException ue, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(ue.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now());


        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReelsException.class)
    public ResponseEntity<ErrorDetails> reelsExceptionHandler(ReelsException ue, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(ue.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now());


        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ErrorDetails> postExceptionHandler(PostException ue, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(ue.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now());


        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ErrorDetails> messageExceptionHandler(MessageException ue, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(ue.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now());


        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ErrorDetails> commentExceptionHandler(CommentException ue, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(ue.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now());


        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ChatException.class)
    public ResponseEntity<ErrorDetails> chatExceptionHandler(ChatException ue, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(ue.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now());


        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDetails> authExceptionHandler(AuthException ue, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(ue.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now());


        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }



//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetails> allExceptionHandler(Exception ue, WebRequest webRequest){
//
//        ErrorDetails errorDetails = new ErrorDetails(ue.getMessage(),
//                webRequest.getDescription(false),
//                LocalDateTime.now());
//
//
//        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
//    }



}
