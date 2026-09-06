package com.springboot.mySpringBootCRUDapp.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests (requests ->
        					requests
                                .requestMatchers("/eCommerceApi/orders/**").authenticated()
                                .requestMatchers("/eCommerceApi/productsRatedStates/**").authenticated()
                                .requestMatchers("/eCommerceApi/**").permitAll())
                .oauth2ResourceServer((oauth2) -> oauth2
                	    .jwt(Customizer.withDefaults())
                		);
        
        http.cors(Customizer.withDefaults());
        
        http.csrf().disable();

        return http.build();
    }
}
