package com.example.mmanalog.configs;

import com.example.mmanalog.fiters.JwtRequestFilter;
import com.example.mmanalog.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableWebSecurity
    public class SpringSecurityConfig {

        public final CustomUserDetailsService customUserDetailsService;

        private final JwtRequestFilter jwtRequestFilter;

        public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
            this.customUserDetailsService = customUserDetailsService;
            this.jwtRequestFilter = jwtRequestFilter;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
            return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(customUserDetailsService)
                    .passwordEncoder(passwordEncoder())
                    .and()
                    .build();
        }

        @Bean
        protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .httpBasic().disable()
                    .cors().and()
                    .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.POST, "/users").permitAll()
                    .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/projectfolders").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/projectfolders/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/photologs").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/photologs/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/filmstockinventories").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/filmstockinventories/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/filmstockinventories").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/filmstockinventories/**").hasRole("ADMIN")
                    .requestMatchers("/authenticated").authenticated()
                    .requestMatchers("/authenticate").permitAll()
                    .anyRequest().denyAll()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }
}
