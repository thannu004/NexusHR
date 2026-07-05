package com.nexushr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nexushr.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

    	http
        		.cors(cors -> {})
        		.csrf(csrf -> csrf.disable())

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/api/auth/**").permitAll()

                        .requestMatchers("/api/admin/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/api/hr/**")
                        .hasAnyRole("ADMIN", "HR")

                        .requestMatchers("/api/employee/**")
                        .hasAnyRole("ADMIN", "HR", "MANAGER", "EMPLOYEE")

                        .requestMatchers("/api/departments/**")
                        .hasAnyRole("ADMIN", "HR")

                        .requestMatchers("/api/employees/**")
                        .hasAnyRole("ADMIN", "HR")

                        .requestMatchers("/api/attendance/**")
                        .hasAnyRole("ADMIN", "HR", "MANAGER", "EMPLOYEE")

                        .requestMatchers("/api/payroll/**")
                        .hasAnyRole("ADMIN", "HR")

                        .requestMatchers("/api/performance/**")
                        .hasAnyRole("ADMIN", "HR", "MANAGER")

                        .requestMatchers("/api/notifications/**")
                        .authenticated()

                        .requestMatchers("/api/pdf/**")
                        .hasAnyRole("ADMIN", "HR")
                        
                        .requestMatchers("/api/dashboard/**")
                        .authenticated()
                        
                        .requestMatchers("/api/nexusbot/**")
                        .authenticated()
                        
                        .requestMatchers("/api/announcements/**")
                        .hasAnyRole("ADMIN","HR","MANAGER","EMPLOYEE")
                        
                        .requestMatchers("/api/documents/**")
                        .hasAnyRole("ADMIN","HR","EMPLOYEE")
                      
                        .anyRequest()
                        .authenticated())

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }

}