//For user authentication 
package com.nagarro.config;
//importing packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.nagarro.service.JwtAuthenticationEntryPoint;
import com.nagarro.service.UserDetailsServiceImp;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	//Classes to check authorization and token generation and validation
	@Autowired
	private UserDetailsServiceImp userDetailsServiceImpl;	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizeHandler;	
	@Autowired
	private JwtAuthenticationFilter jwtAuthentication;
	
	//To store password in encrypted form
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .csrf()
		    .disable()
		    .cors()
		    .disable()
		    .authorizeHttpRequests()
		    .antMatchers("/token","/user/register","/product","product/search/*").permitAll()
		     .antMatchers(HttpMethod.OPTIONS).permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .exceptionHandling().authenticationEntryPoint(unauthorizeHandler)
		    .and()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		    
		http.addFilterBefore(jwtAuthentication, UsernamePasswordAuthenticationFilter.class);
	}

}
