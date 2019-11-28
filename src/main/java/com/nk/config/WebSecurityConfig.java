package com.nk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.nk.service.MyDBAuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	 @Autowired
	 MyDBAuthenticationService myDBAauthenticationService;
	 
	 @Autowired
	 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	 
	    
	       // Các User trong Database
	       auth.userDetailsService(myDBAauthenticationService);
	       auth.authenticationProvider(authenticationProvider());
	 
	   }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		.and().authorizeRequests().antMatchers("/user/**").hasAnyRole("USER","ADMIN")
		.anyRequest().permitAll()
		.and().formLogin().loginPage("/login").loginProcessingUrl("/login_process").usernameParameter("TenDangNhap").passwordParameter("MatKhau").failureUrl("/login_process").defaultSuccessUrl("/login_process")
		.and().exceptionHandling().accessDeniedPage("/")
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
		System.out.println();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/resource/**");
	}
	
 
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myDBAauthenticationService);
        //authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    
 
	
}
