package com.devfox.board.config;

import com.devfox.board.service.user.BoardUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BoardUserDetailsService boardUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(boardUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(
                        PathRequest.toStaticResources().atCommonLocations()
                )
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .formLogin(login ->
                        login
                                .loginPage("/signin")
                                .loginProcessingUrl("/signinprocess")
                                .permitAll()
                                .defaultSuccessUrl("/", true)
                                .failureUrl("/signin?success=false")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/"))
                .exceptionHandling(error ->
                        error.accessDeniedPage("/access-denied")
                )
                .rememberMe()
                .key("remember-me-key")
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("remember-me-cookie")
                .tokenValiditySeconds(60)
                .and()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/admin*").hasRole("ADMIN")

                ;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
