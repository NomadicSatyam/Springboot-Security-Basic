package com.SpringBootSecurity.Config;

import com.SpringBootSecurity.Services.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MySecurityConfig  {

//    @Bean
//    //authentication In Memory
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//       UserDetails admin = User.withUsername("Basant")
//               .password(encoder.encode("Pwd1"))
//                .roles("ADMIN")
//                .build();
//       UserDetails user = User.withUsername("John")
//               .password(encoder.encode("Pwd2"))
//                .roles("USER","HR")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//
//    }

    //authentication
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http   .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "v2/welcome","/v2/newinfo","/v1/**").permitAll()
                        .requestMatchers("/v2/**")
                        .authenticated())
                 .httpBasic(Customizer.withDefaults());


    return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
