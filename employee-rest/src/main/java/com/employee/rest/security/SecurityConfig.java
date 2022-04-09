package com.employee.rest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.employee.rest.service.UserDetailServiceImpl;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailServiceImpl();
	}

//	 @Bean
//	    public HttpSessionStrategy httpSessionStrategy() {
//	        return new HeaderHttpSessionStrategy();
//	    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
	
	//h2 Console UI access Issue
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/h2-console/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/user", "/api/role").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/employees").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/employees").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/employees/delete/*").hasAuthority("ADMIN")
				// .antMatchers("/api/e","/student/delete").hasAuthority("ADMIN")
				.anyRequest().authenticated().and().httpBasic()
//	            .formLogin().loginProcessingUrl("/login").successForwardUrl("/student/list").permitAll()
//	            .and()
//	            .logout().logoutSuccessUrl("/login").permitAll()
//	            .and()
//	            .exceptionHandling().accessDeniedPage("/student/403")
				.and().cors().and().csrf().disable();
	}
	
	
	
	
	
	

	
}
