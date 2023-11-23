package com.votacion.sistema.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.votacion.sistema.dto.response.ApiResponse;
import com.votacion.sistema.security.service.UserDetailsServiceCustom;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    Claims claims = null;

    private String email = null;

    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    private UserDetailsServiceCustom customerDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{

        if (request.getRequestURI().equals("/signup") || request.getRequestURI().equals("/auth/login")) {
            filterChain.doFilter(request, response);
        }else
        {
            //TODO: REEMPLAZAR POR UN ENTRY POINT Y ASOSCIARLOS A LOS HANDLER EXCEPTIONS, NO HACER TRY CATCH
            try {
            String authorizationHeader = request.getHeader("Authorization");
            String token = null;

            if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer "))
            {
                token = authorizationHeader.substring(7);
                email = jwtUtil.extractUsername(token);
            }


            if(email!=null && SecurityContextHolder.getContext().getAuthentication() == null)
            {
                UserDetails userDetails = customerDetailsService.loadUserByUsername(email);
                if(jwtUtil.validateToken(userDetails, token))
                {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    new WebAuthenticationDetailsSource().buildDetails(request);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    System.out.println("se autentico al usuario");
                }
            }
                filterChain.doFilter(request, response);

            }catch (JwtException jwtException)
            {
                ObjectMapper objectMapper = new ObjectMapper();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setError(jwtException.getMessage());
                apiResponse.setStatus(response.getStatus());
                response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
            }


        }


    }


}
