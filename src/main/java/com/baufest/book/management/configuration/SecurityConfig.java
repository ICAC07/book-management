package com.baufest.book.management.configuration;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.baufest.book.management.constant.Constant;
import com.baufest.book.management.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final UserService userService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.authorizeHttpRequests(authorize -> authorize
        			.requestMatchers(HttpMethod.GET, Constant.EndPoint.ROOT.concat(Constant.EndPoint.HEALTH)).permitAll()
        			.requestMatchers(Constant.EndPoint.SWAGGER).permitAll()
        			.requestMatchers(Constant.EndPoint.OTHERS).permitAll()
        			.requestMatchers("/api/v1/auth/**").permitAll()
        			.anyRequest().authenticated()
        )
        .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))	
    	.csrf(AbstractHttpConfigurer::disable)
    	.authenticationProvider(authenticationProvider()).addFilterBefore(
                jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    /*
    @Bean
    public UserDetailsService userDetailsService() {
    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	 UserDetails user = User.withUsername(Constant.EndPoint.Credential.USER)
    	            .password(encoder.encode(Constant.EndPoint.Credential.PASS))
    	            .roles("ADMIN")
    	            .build();

        return new InMemoryUserDetailsManager(user);
    }
    */
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(Constant.EndPoint.ROOT.concat(Constant.EndPoint.COMODIN)).allowedOrigins("*").allowedHeaders("*").allowedMethods(HttpMethod.GET.toString(), HttpMethod.POST.toString(), HttpMethod.DELETE.toString());
            }
        };
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}
