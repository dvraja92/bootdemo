package com.example.bootdemo.config;

import com.example.bootdemo.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    /**
     * @return AuthenticationManager class object
     * @implNote It is just use for authentication providers
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }




    /**
     * @param http : Http Security
     * @throws Exception
     * @implNote : Overridden Method {@link WebSecurityConfigurerAdapter}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET
                        )
                .permitAll()
                .antMatchers( Constants.BASE_PUBLIC_API_URL + "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().and()
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS);

    }

    /**
     * @return BCryptPasswordEncoder class object
     * @implNote It is return BCryptPasswordEncoder object use for password getEncoder
     */
    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return corsConfigurationSource class object
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.setAllowedOrigins( Collections.singletonList("*"));
        configuration.setAllowCredentials(Boolean.TRUE);
        configuration.setAllowedMethods( Arrays.asList("OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
