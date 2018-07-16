package com.example.uninotice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.uninotice.security.user.CustomeUserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomeUserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
    }
    
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index","/registration").permitAll()
                .antMatchers("/style/**","/javascript/**","/images/**","/fonts/**").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login")
                    .usernameParameter("username").passwordParameter("password")
                    .permitAll()
                    .defaultSuccessUrl("/homepage", false)
                .and()
                    .logout().logoutSuccessUrl("/login?logout")
                    .permitAll()
                .and()
                    .exceptionHandling().accessDeniedPage("/403")
                .and()
                    .csrf();
    }

    @Bean(name="passordEncoder")
    public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }
	
}
