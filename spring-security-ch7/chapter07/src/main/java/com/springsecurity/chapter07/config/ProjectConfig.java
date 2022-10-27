package com.springsecurity.chapter07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        // http.authorizeHttpRequests()
        // .anyRequest()
        // .hasAnyRole("ADMIN");

        // Matchers

        // http.authorizeRequests()
        // .mvcMatchers("/hello").hasRole("ADMIN") // 관리자 역할의 사용자만 /hello 경로를 호출할 수 있다.
        // .mvcMatchers("/ciao").hasRole("MANAGER")
        // .anyRequest().authenticated(); // 운영자 역할의 사용자만 /ciao 경로를 호출할 수 있다.

        // http.authorizeRequests()
        // .mvcMatchers(HttpMethod.GET, "/a")
        // .authenticated() // HTTP GET 방식으로 /a 경로를 요청하면 앱이 사용자를 인증해야 한다.
        // .mvcMatchers(HttpMethod.POST, "/a")
        // .permitAll() // HTTP POST 방식으로 /a 경로를 요청하면 모두 허용
        // .anyRequest()
        // .denyAll(); // 다른 경로에 대한 모든 요청 거부

        // http.csrf().disable();

        // http.authorizeRequests()
        // .mvcMatchers("/a/b/**") // /a/b/**식은 접두사 /a/b가 붙은 모든 경로를 나타낸다.
        // .authenticated()
        // .anyRequest()
        // .permitAll();

        // http.csrf().disable();

        // http.authorizeRequests()
        // .mvcMatchers("/product/{code:^[0-9]*$}") // 길이와 관계없이 숫자를 포함하는 문자열을 나타내는 정규식
        // .permitAll()
        // .anyRequest()
        // .denyAll();

        // /a a 경로만
        // /a/* * 하나만 대체
        // /a/** 하위 경로 대체
        // /a/{param:regex} 정규식 매칭하는 경로만 일치

        http.authorizeRequests()
                .mvcMatchers("/hello")
                .authenticated();

    }
}
