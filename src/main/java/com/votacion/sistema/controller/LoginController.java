package com.votacion.sistema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.votacion.sistema.dto.LoginDTO;
import com.votacion.sistema.dto.UserLoguedDTO;
import com.votacion.sistema.dto.response.ApiResponse;
import com.votacion.sistema.dto.response.UserDTO;
import com.votacion.sistema.security.service.UserDetailsServiceCustom;
import com.votacion.sistema.service.AuthService;
import com.votacion.sistema.util.Constants;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth/login")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
//qwewqewqqwqeqw
public class LoginController {
    private final AuthService authService;

    @PostMapping
    public ApiResponse login(HttpServletResponse response,@Valid @RequestBody LoginDTO credentials)
    {
        UserLoguedDTO userLoguedDTO =  authService.login(credentials);
        String token = userLoguedDTO.getToken();
        response.setHeader("Authorization", "Bearer " + token);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userLoguedDTO.getUsername());
        userDTO.setEmail(userLoguedDTO.getEmail());
        userDTO.setCandidado(userLoguedDTO.getCandidato());
        userDTO.setVoto(userLoguedDTO.isVoto());
        userDTO.setToken(token);
       ApiResponse apiResponse = new ApiResponse();
       apiResponse.setStatus(0);
       apiResponse.setPayload(userDTO);
       apiResponse.setMessage(Constants.SUCESS);
       response.setStatus(HttpServletResponse.SC_OK);
       response.setContentType(MediaType.APPLICATION_JSON_VALUE);
       return apiResponse;
    }
}
