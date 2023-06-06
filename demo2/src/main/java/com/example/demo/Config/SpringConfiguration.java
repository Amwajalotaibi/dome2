package com.example.demo.Config;

import com.example.demo.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfiguration {
    private final MyUserDetailsService myUserDetailsService;// هذي عشان نوصل للداتا بيس
    @Bean // هذي ضروريه احطها عشان يشغلها سبرنق
    public DaoAuthenticationProvider authenticationProvider(){ // هذي تتآكد هل اليوزر موجود في الداتا بيس او لا
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);// هذي عشان نوصل للداتا بيس باليوسر نيم
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder()); // هذي عشان نسوي هاش للباس
        return authenticationProvider; // هذي يرجع لي الهاش باس
    }
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())// هنا يتأكد اني مسجله دخول
                .authorizeHttpRequests()// هذي لتوزيع الصلاحيات
                .requestMatchers("/api/v1/auth/register").permitAll()//هذي للكل حتى للي مو مسجل دخول
                .requestMatchers("/api/v1/auth/admin").hasAuthority("ADMIN")// هذي الصفحه مايمتلكها الا الادمين
                .requestMatchers("/api/v1/auth/user").hasAuthority("USER")
                .requestMatchers("/api/v1/auth/login").hasAuthority("USER")
                .anyRequest().authenticated() // هذي بقيت الصفحات ايش تكون احنا سويناها للكل
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")// هذي لتسجيل الخروج
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }

}
