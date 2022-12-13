package com.mvc.project.exceptions;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionController  {

    private static Logger LOGGER = Logger.getLogger(ExceptionController.class);

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public String exceptionHandler(Exception exception) {
        LOGGER.info("START function exceptionHandler");
        LOGGER.debug(exception);
        LOGGER.info("END function exceptionHandler");
        return "errors/400";
    }

    @ExceptionHandler(value = {ClassNotFoundException.class})
    public String notFoundException(Exception exception) {
        LOGGER.error(exception);
        return "errors/404";
    }

//    @ExceptionHandler(value = {Exception.class})
//    public String allException(Exception exception) {
//        LOGGER.error(exception);
//        return "errors/fallback-page";
//    }
}
