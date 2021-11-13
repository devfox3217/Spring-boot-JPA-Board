package com.devfox.board.config;

import com.devfox.board.service.user.BoardUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;


/**
 * The type Security config.
 */
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BoardUserDetailsService boardUserDetailsService;

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(boardUserDetailsService);
    }

    /**
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        web
                .expressionHandler(expressionHandler)
                .ignoring()
                .requestMatchers(
                        PathRequest.toStaticResources().atCommonLocations()
                )
        ;
    }

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .formLogin(login ->
                        login
                                .loginPage("/signin")
                                .loginProcessingUrl("/signinprocess")
                                .permitAll()
                                .defaultSuccessUrl("/main", true)
                                .failureUrl("/signin?success=false")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/signout")
                                .logoutSuccessUrl("/signin"))
                .exceptionHandling(error ->
                        error.accessDeniedPage("/access-denied")
                )
                .rememberMe()
                .key("remember-me-key")
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("remember-me-cookie")
                .userDetailsService(boardUserDetailsService)
                .tokenValiditySeconds(60 * 60 * 24 * 7 * 30)
                .and()
                .authorizeRequests()
                .antMatchers("/main/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/*").permitAll()
                ;
    }

    /**
     * Gets password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
        return roleHierarchy;
    }

    @Bean
    public AccessDecisionVoter<?> roleVoter() {
        return new RoleHierarchyVoter(roleHierarchy());
    }

}
