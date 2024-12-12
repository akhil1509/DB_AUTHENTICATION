package com.example.CRUD.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class UserConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((e) -> {
			e.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
			e.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
			e.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
			e.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
			e.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
			//e.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
			e.requestMatchers("/api/auth/**").permitAll();
			e.anyRequest().authenticated();
		}).httpBasic(Customizer.withDefaults());

		return http.build();
	}
	// in db authentication
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}

//in memory authentication
//	@Bean
//	public UserDetailsService userDetailsService() {
//
//		UserDetails ramesh = User.builder().username("akhil").password(passwordEncoder().encode("akhil"))
//				.roles("USER").build();
//
//		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(ramesh, admin);
//	}

}
