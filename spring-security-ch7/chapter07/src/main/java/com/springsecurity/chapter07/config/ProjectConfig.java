package com.springsecurity.chapter07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // Grandted Authority
        // UserDetails user1 = User.withUsername("john")
        // .password("12345")
        // .authorities("read")
        // .build();

        // UserDetails user2 = User.withUsername("jane")
        // .password("12345")
        // .authorities("write", "read", "delete")
        // .build();

        // ROLE 설정
        // UserDetails user1 = User.withUsername("john")
        // .password("12345")
        // .authorities("ROLE_ADMIN")
        // .build();

        // UserDetails user2 = User.withUsername("jane")
        // .password("12345")
        // .authorities("ROLE_MANAGER")
        // .build();

        UserDetails user1 = User.withUsername("john")
                .password("12345")
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.withUsername("jane")
                .password("12345")
                .roles("MANAGER")
                .build();

        manager.createUser(user1);
        manager.createUser(user2);

        return manager;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        // http.authorizeRequests()
        // .anyRequest().hasAnyAuthority("WRITE", "READ"); // 모든 요청에 대해 액세스를 허용한다.

        // expression 을 이용한 access 사용 예
        // String expression = "hasAuthority('read') and !hasAuthority('delete')";
        // http.authorizeRequests()
        // .anyRequest()
        // .access(expression);

        // Granted Authority 사용
        http.authorizeHttpRequests()
                .anyRequest()
                .hasAnyRole("ADMIN");

    }
}
