package com.votacion.sistema.controller;

import com.votacion.sistema.dao.CandidatoDAO;
import com.votacion.sistema.dto.CandidatoDTO;
import com.votacion.sistema.dto.response.ApiResponse;
import com.votacion.sistema.dto.response.UserDTO;
import com.votacion.sistema.exception.VoteUsed;
import com.votacion.sistema.model.Candidato;
import com.votacion.sistema.model.User;
import com.votacion.sistema.security.service.UserDetailsServiceCustom;
import com.votacion.sistema.service.VotacionService;
import com.votacion.sistema.util.Constants;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/votacion")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@AllArgsConstructor
public class VotacionController {

    private final UserDetailsServiceCustom userDetailsServiceCustom;
    private final VotacionService votacionService;
    //TODO: METER EN UN SERVICIO ESTO NO SE HACE
    private final CandidatoDAO candidatoDAO;
    @GetMapping("/candidatos")
    public ApiResponse getcandidatos(HttpServletResponse response)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(0);
        apiResponse.setPayload(votacionService.getAllCandidatos());
        apiResponse.setMessage(Constants.SUCESS);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        return apiResponse;
    }
    @GetMapping("/candidatosAuth")
    public ApiResponse getcandidatosAuth(HttpServletResponse response)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(0);
        apiResponse.setPayload(votacionService.getAllCandidatosUsuarioLogueado());
        apiResponse.setMessage(Constants.SUCESS);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        return apiResponse;
    }

    @PostMapping("/votar")
    public ApiResponse votar(HttpServletResponse response, @Valid @RequestBody CandidatoDTO candidatoDTO, Principal principal)
    {
        User user = userDetailsServiceCustom.getUserDetail();
        System.out.println(user.getUsername());
        //if(user.getCandidato()!=null)
        //     throw new VoteUsed("El usuario ya voto");
        votacionService.votarCandidato(candidatoDTO,user);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(0);
        UserDTO userDTO = new UserDTO();
        userDTO.setVoto(true);
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        Candidato candidato = candidatoDAO.findById(candidatoDTO.getIdCandidato()).get();
        userDTO.setCandidado(user.getCandidato().getName());
        apiResponse.setPayload(userDTO);
        apiResponse.setMessage(Constants.SUCESS);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        return apiResponse;
    }


}
