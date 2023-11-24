package com.votacion.sistema.controller;

import com.votacion.sistema.dto.LoginDTO;
import com.votacion.sistema.dto.SignUpDTO;
import com.votacion.sistema.dto.response.ApiResponse;
import com.votacion.sistema.dto.response.UserDTO;
import com.votacion.sistema.model.Role;
import com.votacion.sistema.model.User;
import com.votacion.sistema.security.jwt.JwtUtils;
import com.votacion.sistema.security.service.UserDetailsServiceCustom;
import com.votacion.sistema.service.AuthService;
import com.votacion.sistema.service.SignUpService;
import com.votacion.sistema.util.Constants;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SignUpController {
    private final AuthService authService;
    private final SignUpService signUpService;
    private final JwtUtils jwtUtils;
    @PostMapping
    public ApiResponse signup(HttpServletResponse response, @Valid @RequestBody SignUpDTO signUpDTO)
    {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ApiResponse apiResponse = new ApiResponse();
        signUpDTO = signUpService.signup(signUpDTO);
        String token = jwtUtils.generateToken(signUpDTO.getEmail(), Constants.ROL_DEFAULT);
        UserDTO userDTO = new UserDTO();
        userDTO.setVoto(false);
        userDTO.setUsername(signUpDTO.getUsername());
        userDTO.setEmail(signUpDTO.getEmail());
        response.setHeader("Authorization", "Bearer "+token);
        apiResponse.setStatus(0);
        apiResponse.setPayload(userDTO);
        apiResponse.setMessage(Constants.SUCESS);
        return apiResponse;
    }
}
