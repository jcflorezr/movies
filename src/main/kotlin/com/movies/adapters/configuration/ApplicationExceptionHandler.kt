package com.movies.adapters.configuration

import com.movies.domain.exception.DataValidationException
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
@Slf4j
class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataValidationException::class)
    @ResponseBody
    fun handleDataValidationException(ex: DataValidationException): ErrorResponse {
        return ErrorResponse.of(ex)
    }
}