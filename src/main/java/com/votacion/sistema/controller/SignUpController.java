package com.votacion.sistema.controller;

import com.votacion.sistema.dto.LoginDTO;
import com.votacion.sistema.dto.SignUpDTO;
import com.votacion.sistema.dto.response.ApiResponse;
import com.votacion.sistema.service.AuthService;
import com.votacion.sistema.service.SignUpService;
import com.votacion.sistema.util.Constants;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.awt.font.ShapeGraphicAttribute;

@AllArgsConstructor
@RestController
@RequestMapping("/signup")
public class SignUpController {
    private final AuthService authService;
    private final SignUpService signUpService;
    @PostMapping
    public ApiResponse signup(HttpServletResponse response, @RequestBody SignUpDTO signUpDTO)
    {

        signUpDTO = signUpService.signup(signUpDTO);
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPassword(signUpDTO.getPassword());
        loginDTO.setEmail(signUpDTO.getEmail());
        String token = authService.login(loginDTO);
        response.setHeader("Authorization", "Bearer "+token);
        signUpDTO.setPassword("*********");
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(0);
        apiResponse.setPayload(signUpDTO);
        apiResponse.setMessage(Constants.SUCESS);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        return apiResponse;
    }
}
