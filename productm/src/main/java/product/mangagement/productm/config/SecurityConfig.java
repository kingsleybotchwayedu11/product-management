package product.mangagement.productm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import product.mangagement.productm.service.user.UserService;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private JsonFileter jsonfilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CSRF for stateless authentication (JWT)
        http.csrf(customizer -> customizer.disable());
        
        // Define public endpoints
        http.authorizeHttpRequests(request -> 
            request.requestMatchers("/user", "/user/login").permitAll()
        );

        // Apply stateless session policy
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add the JWT filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jsonfilter, UsernamePasswordAuthenticationFilter.class);

        // All other requests must be authenticated
        http.authorizeHttpRequests(customizer -> customizer.anyRequest().authenticated());
        
        return http.build();
    }

    // Password Encoder for production use
    @Bean
    @Primary
    public PasswordEncoder passwordEncoderr() {
        return new BCryptPasswordEncoder();
    }

   @Bean
    public DaoAuthenticationProvider authentication(UserService userService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoderr());
        return provider;
    } 

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    } 
}
