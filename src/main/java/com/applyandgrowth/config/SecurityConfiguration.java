package com.applyandgrowth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.applyandgrowth.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UserService userService;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// http
		// 	.authorizeHttpRequests((requests) -> requests
		// 		.requestMatchers("/", "/home").permitAll()
		// 		.anyRequest().authenticated()
		// 	)
		// 	.formLogin((form) -> form
		// 		.loginPage("/login")
		// 		.permitAll()
		// 	)
		// 	.logout((logout) -> logout.permitAll());

		http
			.authorizeHttpRequests((requests) -> requests
			.requestMatchers(
				"/registration**",
				"/js/**",
				"/css/**",
				"/img/**").permitAll()
	   		.anyRequest().authenticated()
	   )
	   .formLogin((form) -> form
	   		.loginPage("/login")
	   		.permitAll()
	   )
	   .logout()
	   .invalidateHttpSession(true)
	   .clearAuthentication(true)
	   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	   .logoutSuccessUrl("/login?logout")
	   .permitAll();

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
