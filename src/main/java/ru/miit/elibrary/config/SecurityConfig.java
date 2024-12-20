package ru.miit.elibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import ru.miit.elibrary.services.AppUserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableTransactionManagement
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsService();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   SecurityContextRepository securityContextRepository)
            throws Exception {
        final String[] SWAGGER_ENDPOINTS = {
                "/swagger-ui/**",
                "/v*/api-docs/**",
                "/swagger-resources/**"
        };
        final String[] ADMIN_ENDPOINTS = {
                "/auth/check-adm",
                "/api/adm/**"
        };
        final String[] TEACHER_ENDPOINTS = {
                "/auth/check-teacher",
                "/api/teacher/**"
        };
        final String[] RESOURCES_ENDPOINTS = {
                "/css/**",
                "/js/**",
                "/res/**",
                "/./templates/schemes/**",
                "/static/favicon.ico"
        };
        final String[] NON_AUTHORIZED_ENDPOINTS = {
                "/check-email",
                "/login",
                "/register",
                "/confirm-account"
        };
        final String[] temp_DEVELOPER_ENDPOINTS = {
                "/auth/check-dev",
                "/api/user/changeUser",
        };
        http
                .httpBasic(withDefaults())
                //.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers(RESOURCES_ENDPOINTS).permitAll()
                                .requestMatchers(NON_AUTHORIZED_ENDPOINTS).permitAll()
                                .requestMatchers(SWAGGER_ENDPOINTS).permitAll()
                                .requestMatchers(temp_DEVELOPER_ENDPOINTS).hasAuthority("DEV")
                                .requestMatchers(ADMIN_ENDPOINTS).hasAuthority("ADMIN")
                                .requestMatchers(TEACHER_ENDPOINTS).hasAuthority("TEACHER")
                                .anyRequest().permitAll() // permitAll если нужно чтобы любой мог любые другие запросы делать
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/loginProcessing")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout ->
                        logout.logoutUrl("/auth/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                )
                .securityContext(
                        securityContext -> securityContext
                                .securityContextRepository(securityContextRepository)
                );
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
