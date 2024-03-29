package com.example.Backend_Spring.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain modifiedSecurityFilterChain(HttpSecurity http) throws Exception
	{
		CsrfTokenRequestAttributeHandler reqHandler=new CsrfTokenRequestAttributeHandler();
		reqHandler.setCsrfRequestAttributeName("_csrf");
		http.securityContext().requireExplicitSave(false).and().sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)).cors().configurationSource(new CorsConfigurationSource()
				{

					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						// TODO Auto-generated method stub
						CorsConfiguration config=new CorsConfiguration();
						config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000/**"));
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setMaxAge(3600L);
						return config;
					}
				}
				
			).and().csrf().disable()//csrf((csrf)->csrf.csrfTokenRequestHandler(reqHandler).ignoringRequestMatchers("/contact","/register").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())).addFilterAfter(new CostCookieFilter(),BasicAuthenticationFilter.class)
				.authorizeHttpRequests(authorize -> authorize
			.requestMatchers("/h2-console/**","/users/saveUser","users/getUser","/saveUser").permitAll().anyRequest().authenticated() 
		)
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
        //http.csrf().ignoringRequestMatchers("/h2-console/**");
        //http.headers().frameOptions().sameOrigin();
        

	return http.build();
		
	}
	
	/*@Bean
	InMemoryUserDetailsManager userDetailsService()
	{
		UserDetails admin=User.withUsername("ayush").password("1234").authorities("admin").build();
		UserDetails user=User.withUsername("utkarsh").password("1234").authorities("user").build();

		
		return new InMemoryUserDetailsManager(admin,user);
	}*/
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
