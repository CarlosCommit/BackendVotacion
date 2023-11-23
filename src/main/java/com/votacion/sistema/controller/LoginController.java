package com.votacion.sistema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.votacion.sistema.dto.LoginDTO;
import com.votacion.sistema.dto.response.ApiResponse;
import com.votacion.sistema.service.AuthService;
import com.votacion.sistema.util.Constants;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth/login")
public class LoginController {
    private final AuthService authService;
    @PostMapping
    public ApiResponse login(HttpServletResponse response,@RequestBody LoginDTO credentials)
    {
        String token = authService.login(credentials);
        response.setHeader("Authorization", "Bearer " + token);
       ApiResponse apiResponse = new ApiResponse();
       apiResponse.setStatus(0);
       apiResponse.setMessage(Constants.SUCESS);
       response.setStatus(HttpServletResponse.SC_OK);
       response.setContentType(MediaType.APPLICATION_JSON_VALUE);
       return apiResponse;
    }
}
