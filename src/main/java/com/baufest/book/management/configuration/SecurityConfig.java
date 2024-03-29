package com.baufest.book.management.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.baufest.book.management.constant.Constant;
import com.baufest.book.management.constant.Role;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.authorizeHttpRequests(authorize -> authorize
        			.requestMatchers(HttpMethod.GET, Constant.EndPoint.ROOT.concat(Constant.EndPoint.HEALTH)).permitAll()
        			.requestMatchers(Constant.EndPoint.SWAGGER).permitAll()
        			.anyRequest().authenticated()
        	
        )
    	.csrf(AbstractHttpConfigurer::disable)
    	.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		UserDetails user = User.withUsername(Constant.EndPoint.Credential.USER)
			.password(encoder.encode(Constant.EndPoint.Credential.PASS))
			.roles(Role.USER.name())
			.build();
		
		UserDetails userAdmin = User.withUsername(Constant.EndPoint.Credential.ADMIN)
			.password(encoder.encode(Constant.EndPoint.Credential.PASS))
			.roles(Role.ADMIN.name())
			.build();

        return new InMemoryUserDetailsManager(Arrays.asList(user, userAdmin));
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(Constant.EndPoint.ROOT.concat(Constant.EndPoint.COMODIN)).allowedOrigins("*").allowedHeaders("*").allowedMethods(HttpMethod.GET.toString(), HttpMethod.POST.toString(), HttpMethod.DELETE.toString());
            }
        };
    }
}
