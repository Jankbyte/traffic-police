package ru.jankbyte.trafficpolice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.core.annotation.Order;

/**
 * Конфиг-настроек для Spring security.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>
            .AuthorizationManagerRequestMatcherRegistry> securedRequests = authorize -> {
        authorize.requestMatchers("/login*", "/web-res/**").permitAll()
            .requestMatchers("/api/user/**", "/user/**").hasRole("ROOT")
            .anyRequest().authenticated();
    };

    /**
     * Фильтр для basic-авторизации (<code>httpBasic</code>).
     * <p>Данный фильтр запускается в том случае - если
     * в REST-запросе есть заголовок (header) - Authorization.</p>
     * <p>Фильтр не имеет сессии и CSRF-защиты. Является приоритетным
     * над <code>formFilterChain</code></p>
     */
    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(
            HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(
                SessionCreationPolicy.NEVER).and()
            .authorizeHttpRequests(securedRequests)
            .securityMatcher(request ->
                request.getHeader("Authorization") != null)
            .httpBasic(basic ->
                basic.authenticationEntryPoint(
                    (request, response, exp)->
                        response.setStatus(401)))
            .build();
    }

    /**
     * Фильтр для авторизацию через форму (<code>formLogin</code>).
     * <p>Данный фильтр запускается в том случае, если запрос
     * не подходит по условию для <code>apiFilterChain</code>.</p>
     * <p>Имеет включеную CSRF-защиту и сессию.</p>
     */
    @Bean
    public SecurityFilterChain formFilterChain(
            HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .authorizeHttpRequests(securedRequests)
            .formLogin(form ->
                form.loginPage("/login")
                    .failureUrl("/login?error"))
            .logout(logout ->
                logout.logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID"))
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
