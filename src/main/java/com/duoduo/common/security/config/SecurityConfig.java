package com.duoduo.common.security.config;

import com.duoduo.common.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //将编写的UserDetailsService注入进来
        provider.setUserDetailsService(userDetailsService);
        //将使用的密码编译器加入进来
        provider.setPasswordEncoder(passwordEncoder);
        //将provider放置到AuthenticationManager 中
        ProviderManager providerManager = new ProviderManager(provider);
        return providerManager;
    }

    /*
     * 在security安全框架中，提供了若干密码解析器实现类型。
     * 其中BCryptPasswordEncoder 叫强散列加密。可以保证相同的明文，多次加密后，
     * 密码有相同的散列数据，而不是相同的结果。
     * 匹配时，是基于相同的散列数据做的匹配。
     * Spring Security 推荐使用 BCryptPasswordEncoder 作为密码加密和解析器。
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * 配置权限相关的配置
     * 安全框架本质上是一堆的过滤器，称之为过滤器链，每一个过滤器链的功能都不同
     * 设置一些链接不要拦截
     * */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//       关闭csrf
        httpSecurity.csrf(it -> it.disable());
        httpSecurity.authorizeHttpRequests(it ->
                it.requestMatchers("/login").permitAll()  //设置登录路径所有人都可以访问
                        .anyRequest().authenticated()  //其他路径都要进行拦截
        );
//        //表单
        httpSecurity.formLogin(from ->
                from.loginPage("/login")   //跳转到自定义的登录页面
                        .loginProcessingUrl("/login")  //处理前端的请求，与from表单的action一致即可
                        .defaultSuccessUrl("/index")  //默认的请求成功之后的跳转页面，直接访问登录页面
        );
        return httpSecurity.build();
    }
}
