package com.votacion.sistema.controller;

import com.votacion.sistema.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votacion")
public class VotacionController {
    @GetMapping("/candidatos")
    public ApiResponse getcandidatos()
    {
        System.out.println("Entrando correctamente");
        return null;
    }
}
