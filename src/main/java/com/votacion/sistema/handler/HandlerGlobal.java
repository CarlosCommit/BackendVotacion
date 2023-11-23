package com.votacion.sistema.handler;

import com.votacion.sistema.dto.response.ApiResponse;
import com.votacion.sistema.exception.RegisterDuplicated;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerGlobal {

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiResponse> handleLoginException( BadCredentialsException e)
    {
        return generateApiResponseError("No se encontro una cuenta asociada", e);

    }
    @ExceptionHandler(value = RegisterDuplicated.class)
    public ResponseEntity<ApiResponse> handleRegistredDuplicated( RegisterDuplicated e)
    {
        return generateApiResponseError(e.getMessage(), e);

    }
    private ResponseEntity<ApiResponse> generateApiResponseError(String error, Exception e)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(error + " " + e);
        apiResponse.setStatus(400);
        return  new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
