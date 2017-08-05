package com.nraj.authapi.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private MyAppUserDetailsService myAppUserDetailsService;
	
	@Autowired
	private AppAuthenticationEntryPoint appAuthenticationEntryPoint;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	public void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .authorizeRequests()
		    .antMatchers("/login").permitAll()
			.antMatchers("/signup").permitAll()
		  	.antMatchers("/authorized/**").hasAnyRole("ADMIN","USER").anyRequest()
		  	.authenticated().and().csrf().disable().formLogin()
		  	.loginProcessingUrl("/login").failureUrl("/login?error=true")
		  	.successForwardUrl("/authorized/api/v1/success/{email}")
			.usernameParameter("email")
			.passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").and().exceptionHandling()
			.accessDeniedPage("/login-error");
	} 
//    @Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
//	}
}