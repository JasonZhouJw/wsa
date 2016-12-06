package com.alpha.web.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by jzhou237 on 2016-12-05.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().antMatchers("/css/**", "/webjars/**").permitAll()
//                .antMatchers("/h2-console/**").permitAll()
//                .anyRequest().authenticated()
//                .antMatchers("/home").permitAll()
//                .and()
//                .formLogin().defaultSuccessUrl("/").loginPage("/signin").permitAll()
//                .and()
//                .logout().logoutUrl("/signout").permitAll();
        http.csrf().disable();
    }

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(this.dataSource).usersByUsernameQuery("select name, password from users where name=?")
                .authoritiesByUsernameQuery("select name,'admin' from users where name=?");
    }


}
