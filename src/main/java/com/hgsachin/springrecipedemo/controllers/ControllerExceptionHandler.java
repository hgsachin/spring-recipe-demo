package com.hgsachin.springrecipedemo.controllers;

import com.hgsachin.springrecipedemo.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception ex) {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("exceptions/badRequest");
        log.error(ex.getMessage());
        mnv.addObject("exe", ex.getMessage());
        return mnv;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(Exception ex) {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("exceptions/404NotFound");
        mnv.addObject("exe", ex.getMessage());
        log.error(ex.getMessage());
        return mnv;
    }
}
