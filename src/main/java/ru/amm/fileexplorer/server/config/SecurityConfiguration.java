package ru.amm.fileexplorer.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

import java.security.AuthProvider;
import java.util.Arrays;

@Controller
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
                .authorizeRequests()
				    .antMatchers("/resources", "/error")
                        .permitAll()
                            .anyRequest()
                    .authenticated()
                .and()
                        .formLogin()
                            .loginPage("/login")
				                .loginProcessingUrl("/perform_login")
                                    .usernameParameter("username")
                                    .passwordParameter("password")
                                .successHandler(loginSuccessHandler()).permitAll()
				                .failureUrl("/error")
                .permitAll()
				.and()
				    .logout()
                    .permitAll();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		UserDetails user = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user);
	}

    private AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) ->{
            response.sendRedirect("/");
        };
    }




}
