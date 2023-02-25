package com.samgyeobsal.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public RoleHierarchyImpl roleHierarchyImpl() {
        RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
        roleHierarchyImpl
                .setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
        return roleHierarchyImpl;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeHttpRequests((authz)->
                        authz
                                .anyRequest().permitAll()
                )
                .formLogin()
                .loginPage("/web/account/login")
                .defaultSuccessUrl("/web")
                .failureHandler(authenticationFailureHandler)
        .and()
                .oauth2Login()
                .loginPage("/web/account/login")
                .defaultSuccessUrl("/web")
        .and()
                .logout()
                .logoutUrl("/web/account/logout")
                .logoutSuccessUrl("/web")
                .deleteCookies("JSESSIONID" , "remember-me");


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/resources/**");
    }
}
