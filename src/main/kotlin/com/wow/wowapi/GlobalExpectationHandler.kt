// src/main/kotlin/com/example/demo/exception/GlobalExceptionHandler.kt
package com.wow.wowapi

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(
        ex: MethodArgumentNotValidException
    ): ResponseEntity<Map<String, String>> {
        val errors = ex.bindingResult.allErrors.associate { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage ?: "Invalid value"
            fieldName to errorMessage
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(
        ex: ConstraintViolationException
    ): ResponseEntity<Map<String, String>> {
        val errors = ex.constraintViolations.associate { violation ->
            val fieldName = violation.propertyPath.toString()
            val errorMessage = violation.message
            fieldName to errorMessage
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}
