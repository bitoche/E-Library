package ru.miit.elibrary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
import ru.miit.elibrary.services.AppUserDetailsService;
import ru.miit.elibrary.services.ElibAuthenticationProvider;

import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
//@EnableWebSecurity
//@EnableTransactionManagement
public class SecurityConfig{
    @Autowired
    @Lazy
    private ElibAuthenticationProvider customAuthenticationProvider;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

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
                "/auth/login",
                "/register",
                "/confirm-account"
        };
        final String[] temp_DEVELOPER_ENDPOINTS = {
                "/auth/check-dev",
                "/api/user/changeUser",
        };
        http
                .authenticationProvider(customAuthenticationProvider)
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/auth/loginProcessing").permitAll()
                                .requestMatchers(RESOURCES_ENDPOINTS).permitAll()
                                .requestMatchers(NON_AUTHORIZED_ENDPOINTS).permitAll()
                                .requestMatchers(SWAGGER_ENDPOINTS).permitAll()

                                .requestMatchers(temp_DEVELOPER_ENDPOINTS).hasAuthority("DEV")
                                .requestMatchers(ADMIN_ENDPOINTS).hasAuthority("ADMIN")
                                .requestMatchers(TEACHER_ENDPOINTS).hasAuthority("TEACHER")
                                .requestMatchers("/auth/login", "/auth/logout").permitAll()
                                .anyRequest().authenticated()
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
                                .deleteCookies("JSESSIONID") // Удалить куки
                                .permitAll()
                )
                .securityContext(
                        securityContext -> securityContext
                                .securityContextRepository(securityContextRepository)
                );
        return http.build();
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService());
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}