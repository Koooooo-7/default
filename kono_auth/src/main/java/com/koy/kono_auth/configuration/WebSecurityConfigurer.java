package com.koy.kono_auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * @Description security 配置
 * @Auther Koy  https://github.com/Koooooo-7
 * @Date 2020/05/04
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    KonoUsernamePasswordAuthenticationFilter konoUsernamePasswordAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 跨域预检请求 options请求放行
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

        // 为无状态请求 不创建也不使用session
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().cacheControl();

        // TODO: 请求权限配置




        // 拦截登录认证信息 在UsernamePasswordAuthenticationFilter过滤器之前进行处理
        http.addFilterAt(konoUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 拦截token
//        http.addFilterAt()
                



    }

}
