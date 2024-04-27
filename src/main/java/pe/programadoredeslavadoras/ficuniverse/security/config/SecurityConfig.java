package pe.programadoredeslavadoras.ficuniverse.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors().and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/ficuniverse/v1/auth/**", "/api/ficuniverse/v1/fanfics/**").permitAll()
                        .requestMatchers("/api/ficuniverse/v1/users/**","/api/ficuniverse/v1/categories/**","/api/ficuniverse/v1/chapters/**", "/api/ficuniverse/v1/fanfics/**", "/api/ficuniverse/v1/profiles/**").authenticated()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        // cuando se establece en STATELESS, significa que no se creará ni
                        // mantendrá ninguna sesión HTTP en el servidor.
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
