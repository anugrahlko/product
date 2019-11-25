package com.product.in.nfo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	String[] roles = new String[] {"ADMIN","USER","MGMT"};
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password(getPasswordEncoder().encode("admin"))
			.roles("ADMIN")
		.and()
			.withUser("user")
			.password(getPasswordEncoder().encode("user"))
			.roles("USER")
		.and()
			.withUser("mgmt")
			.password(getPasswordEncoder().encode("mgmt"))
			.roles("MGMT");
	}
	
	// Security For all API
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
	} 
	*/
	
	//URL path based Security
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/product/edit/**").fullyAuthenticated().and().httpBasic();
	}
	*/
	
	//Role Based Security
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
			.authorizeRequests()
			.antMatchers("/product**").fullyAuthenticated()
			//.antMatchers("/product/delete**").hasRole("ADMIN")
			//.antMatchers("/product/get**").authenticated()
			.and()
			.httpBasic();
	}
	@ExceptionHandler({RuntimeException.class})
	@ResponseBody	
    public Response<ResponseEntity<String>,String> handleRunTimeException(RuntimeException e) {
		Response<ResponseEntity<String>, String> r = new Response<ResponseEntity<String>, String>();
		r.setData(new ResponseEntity<String>("Error Invalid Credentials",HttpStatus.INTERNAL_SERVER_ERROR));
		r.setMessage("Try Again");
		return r;
    }
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
