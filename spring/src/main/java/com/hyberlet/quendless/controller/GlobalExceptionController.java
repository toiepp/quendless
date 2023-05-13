package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.controller.exceptions.AccessDeniedException;
import com.hyberlet.quendless.controller.exceptions.BadRequestException;
import com.hyberlet.quendless.controller.exceptions.EntityNotFoundException;
import com.hyberlet.quendless.model.dto.ServerMessage;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(value = {BadRequestException.class, IllegalArgumentException.class, ConversionFailedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ServerMessage> handleBadRequest(RuntimeException ex) {
        return new ResponseEntity<>(new ServerMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ServerMessage> handleEntityNotFound(RuntimeException ex) {
        return new ResponseEntity<>(new ServerMessage(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ServerMessage> handleAccessDenied(RuntimeException ex) {
        return new ResponseEntity<>(new ServerMessage(ex.getMessage()), HttpStatus.FORBIDDEN);
    }
}
