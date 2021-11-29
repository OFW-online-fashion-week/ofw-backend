package com.ofw.ofw.security;

import com.ofw.ofw.exception.ExceptionHandlerFilter;
import com.ofw.ofw.security.jwt.FilterConfigure;
import com.ofw.ofw.security.jwt.JwtTokenProvider;
import com.ofw.ofw.security.logging.RequestLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final RequestLogger requestLogger;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .csrf().disable()
                .cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/auth/google").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/google").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/brand/signup").permitAll()
                .antMatchers(HttpMethod.GET, "/auth/brand/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/upload").permitAll()
                .antMatchers(HttpMethod.GET, "/admin").permitAll()
                .antMatchers(HttpMethod.GET, "/admin/{id}").permitAll()
                .antMatchers(HttpMethod.HEAD, "/admin/email/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/admin/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/admin/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/brand").permitAll()
                .antMatchers(HttpMethod.GET, "/brand/{brand_id}").permitAll()
                .anyRequest().authenticated()
                .and().apply(new FilterConfigure(jwtTokenProvider, exceptionHandlerFilter, requestLogger));
    }
}
