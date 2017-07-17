package com.food.delivery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by mz on 16/07/17.
 *
 * Provides user authentication through Spring Security and configures pages access.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        // authentication
        auth.inMemoryAuthentication().withUser("w").password("w").roles("USER");
        auth.inMemoryAuthentication().withUser("q").password("q").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").usernameParameter("userId").passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/admin").failureUrl("/login?error");
        http.logout().logoutSuccessUrl("/login?logout");
        http.exceptionHandling().accessDeniedPage("/login?accessDenied");
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')");
        http.csrf().disable();
    }
}
