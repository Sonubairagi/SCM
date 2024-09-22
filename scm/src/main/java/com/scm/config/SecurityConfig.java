package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private OauthAuthenticationSuccessHandler handler;

    //configuration of Security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        //login configuration
        httpSecurity.formLogin(login -> {
            login.loginPage("/login")
            .loginProcessingUrl("/authenticate")
            .successForwardUrl("/user/dashboard")
            // .failureForwardUrl("/login?error=true")
            .usernameParameter("email")
            .passwordParameter("password");
        });

        //logout configurationn
        httpSecurity.logout(logout -> {
            logout.logoutUrl("/do-logout")
            .logoutSuccessUrl("/login?error=true");
        });

        //CSRF Token Configuration
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        //oauth configuration
        // httpSecurity.oauth2Login(Customizer.withDefaults());
        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login")
            .successHandler(handler);
        });

        return httpSecurity.build();
    }

    //configuration of authentication provider
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());

        return provider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
