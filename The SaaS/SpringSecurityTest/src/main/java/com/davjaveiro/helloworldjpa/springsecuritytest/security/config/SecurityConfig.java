package com.davjaveiro.helloworldjpa.springsecuritytest.security.config;

import com.davjaveiro.helloworldjpa.springsecuritytest.auth.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Bean para registrar o CustomUserDetailsService como UserDetailsService

    @Bean
    public UserDetailsService userDetailsService(CustomerUserDetailsService customerUserDetailsService) {
        return customerUserDetailsService;
    }

    // Configuração de segurança usando SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // Rotas públicas (não precisam de autenticação)
                        .anyRequest().authenticated()              // Todas as outras rotas precisam de autenticação
                )
                .formLogin(form -> form                         // Configuração do formulário de login
                        .loginPage("/login")                        // Página personalizada de login (opcional)
                        .permitAll()                                // Permite acesso à página de login
                )
                .logout(logout -> logout                        // Configuração do logout
                        .logoutUrl("/logout")                       // URL para logout
                        .logoutSuccessUrl("/login?logout")          // Redireciona após logout
                        .permitAll()
                );
        return http.build();
    }
}