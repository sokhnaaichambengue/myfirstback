package isepddiamniadio.pff.Gestion_performance.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests -> {
                    // Public access - accès sans authentification
                    authorizeRequests.requestMatchers("/auth/login", "/auth/inscrire", "/ping").permitAll();

                    // Restricted access for Admin
                    authorizeRequests.requestMatchers("/admin/**").hasRole("ADMIN");

                    // Restricted access for Chef de division
                    authorizeRequests.requestMatchers("/chef-division/**").hasRole("CHEF_DIVISION");

                    // Restricted access for Employe
                    authorizeRequests.requestMatchers("/employe/**").hasRole("EMPLOYE");

                    // Restricted access for Agent Commercialisation
                    authorizeRequests.requestMatchers("/api/agentcommercialisation").hasRole("AGENT_COMMERCIALISATION");

                    // Other requests - requires authentication for other endpoints
                    authorizeRequests.anyRequest().authenticated();
                });

        return http.build();
    }
}
