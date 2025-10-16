package com.club.api.club_managment_api.config;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	private final CustomUserDetailsService customUserDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	

	
	public SecurityConfig(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder,
			JwtAuthenticationFilter jwtAuthenticationFilter) {
		super();
		this.customUserDetailsService = customUserDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.jwtAuthenticationFilter=jwtAuthenticationFilter;
	}





	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .headers(headers -> headers.frameOptions(frame->frame.disable())) 
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/student/register**", "/h2-console/**").permitAll() // note **
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form.disable())
	        .httpBasic(basic->basic.disable())
	        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); 

	    return http.build();
	}


	  @Bean public PasswordEncoder passwordEncoder() { 
		  
		  return new BCryptPasswordEncoder(); 
		  
	  }
	  
	  @Bean
	  public AuthenticationProvider authenticationProvider() {
		  DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		  provider.setUserDetailsService(customUserDetailsService);
		  provider.setPasswordEncoder(passwordEncoder);
		  return provider;
	    }
	  
	  @Bean
	    public AuthenticationManager authenticationManager() {
	        return new ProviderManager(List.of(authenticationProvider()));
	    }
	  
} 

