package com.samgyeobsal.security.config;

import com.samgyeobsal.security.filter.JwtTokenFilter;
import com.samgyeobsal.security.handler.CustomAccessDeniedHandler;
import com.samgyeobsal.security.handler.CustomAuthenticationEntryPoint;
import com.samgyeobsal.security.handler.OAuth2SuccessHandler;
import com.samgyeobsal.security.provider.JwtTokenProvider;
import com.samgyeobsal.security.service.OAuth2DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private OAuth2DetailsService oAuth2DetailsService;

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
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .authorizeHttpRequests((authz) ->
                        authz
                                .antMatchers("/web/mypage/**").hasRole("USER")
                                .anyRequest().permitAll()
                )
                .oauth2Login()
                .userInfoEndpoint().userService(oAuth2DetailsService)
                .and()
                .successHandler(new OAuth2SuccessHandler(jwtTokenProvider))
        .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/resources/**");
    }
}
