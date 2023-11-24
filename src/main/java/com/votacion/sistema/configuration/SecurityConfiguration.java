package com.votacion.sistema.configuration;

import com.votacion.sistema.security.jwt.JwtFilter;
import com.votacion.sistema.security.jwt.JwtUtils;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

   private final Integer numberCod;

    @Autowired
    public SecurityConfiguration(@Value("${number.cod}") Integer numberCod)
    {
        this.numberCod = numberCod;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter jwtFilter)throws Exception
    {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.disable())
                .authorizeHttpRequests( authorize -> authorize
                        .requestMatchers("/auth/login", "/signup","/votacion/candidatos").permitAll()
                        .anyRequest().permitAll())
                .securityContext((context) -> context
                        .securityContextRepository(new NullSecurityContextRepository())
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder(numberCod);
    }
    @Bean
    AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
