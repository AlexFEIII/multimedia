package com.example.multimedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

    @Bean
    public SimpleUrlAuthenticationFailureHandler loginFailure(){
        LoginFailureConfig loginFailureConfig = new LoginFailureConfig();
        return loginFailureConfig;
    }

    @Bean
    public SimpleUrlAuthenticationSuccessHandler loginSuccess(){
        LoginSuccessConfig loginSuccessConfig = new LoginSuccessConfig();
        return loginSuccessConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and().formLogin().loginProcessingUrl("/login")
                .successHandler(loginSuccess())
                .failureHandler(loginFailure())
                .and().rememberMe().tokenValiditySeconds(60*60*24*7)
        .and().csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }
}
