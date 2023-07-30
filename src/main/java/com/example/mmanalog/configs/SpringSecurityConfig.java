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
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
            return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(customUserDetailsService)
                    .passwordEncoder(passwordEncoder())
                    .and()
                    .build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .httpBasic().disable()
                    .cors().and()
                    .authorizeHttpRequests()

                    //----------------------------------------User--------------------------------------//
                    .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/users/{username}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.GET,"/users/emails/{email}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/users/{username}/authorities").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/users/{username}/authorities").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/users/register").permitAll()
                    .requestMatchers(HttpMethod.POST,"/users/{username}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.PUT,"/users/{username}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.PUT, "/users/{username}/images/{imageId}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.DELETE, "/users/{username}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE,"/users/{username}/authorities").hasRole("ADMIN")

                    //----------------------------------------Authentication--------------------------------------//
                    .requestMatchers(HttpMethod.GET,"/authenticated").authenticated()
                    .requestMatchers(HttpMethod.POST,"/authenticate").permitAll()

                    //----------------------------------------Project Folder--------------------------------------//
                    .requestMatchers(HttpMethod.GET,"/projectfolders").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/projectfolders/{id}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.GET,"/projectfolders/user/{username}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.POST, "/projectfolders/new").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/projectfolders/new/{username}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.PUT,"/projectfolders/{id}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.DELETE,"/projectfolders/{id}").hasAnyRole("ADMIN", "USER")

                    //----------------------------------------Photo Log--------------------------------------//
                    .requestMatchers(HttpMethod.GET,"/photologs").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/photologs/{id}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.GET,"/photologs/user/{username}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.GET,"/photologs/film_stock/{filmStock}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.POST,"/photologs/new").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/photologs/new/{username}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.POST,"/photologs/new/{username}/folder/{folderId}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.PUT,"/photologs/{id}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.DELETE,"/photologs/{id}").hasAnyRole("ADMIN", "USER")

                    //----------------------------------------Film Stock Inventory--------------------------------------//
                    .requestMatchers(HttpMethod.GET, "/filmstockinventories").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/filmstockinventories/{id}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.GET,"/filmstockinventories/user/{username}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.POST,"/filmstockinventories/new").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/filmstockinventories/new/{username}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.PUT,"/filmstockinventories/{id}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.DELETE,"/filmstockinventories/{id}").hasAnyRole("ADMIN", "USER")

                    //----------------------------------------Film Development Log--------------------------------------//
                    .requestMatchers(HttpMethod.GET, "/filmdevelopmentlogs").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/filmdevelopmentlogs/{id}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.GET, "/filmdevelopmentlogs/user/{username}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.POST,"/filmsdevelopmentlogs/new").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/filmdevelopmentlogs/new/{username}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.PUT,"/filmdevelopmentlogs/{id}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.DELETE,"/filmdevelopmentlogs/{id}").hasAnyRole("ADMIN", "USER")

                    //----------------------------------------File--------------------------------------//
                    .requestMatchers(HttpMethod.GET,"/download/{fileName}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.GET,"/download/{username}/{fileName}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.POST,"/upload").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.POST,"/upload/{username}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.DELETE,"/delete/{username}/{fileName}").hasAnyRole("ADMIN", "USER")

                    .anyRequest().denyAll()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }
}
