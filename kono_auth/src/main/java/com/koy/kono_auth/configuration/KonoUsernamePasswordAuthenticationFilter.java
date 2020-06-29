package com.koy.kono_auth.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koy.kono_auth.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 定义自己的获取前台认证信息的Filter, 默认表单获取参数不符合要求
 * @Auther Koy  https://github.com/Koooooo-7
 * @Date 2020/05/04
 */
@Component
public class KonoUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    UserService userService;

    private static final String SPRING_SECURITY_APP_KEY = "appName";
    private static final String SPRING_SECURITY_API_KEY = "apiKey";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 如果不是Json信息，放行
        if (!request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            this.attemptAuthentication(request, response);
        }


        // 获取前台传过来的认证信息
        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> authData = new HashMap<>();
        try (InputStream is = request.getInputStream()) {
            authData = mapper.readValue(is, Map.class);
        } catch (IOException e) {
            // TODO: 异常处理
            e.printStackTrace();
        }

        if (!authData.isEmpty()) {
            String appName = authData.get(SPRING_SECURITY_APP_KEY);
            String apiKey = authData.get(SPRING_SECURITY_API_KEY);

            if (userService.authenticate(appName, apiKey)) {
                // 存在则装入UsernamePasswordAuthenticationToken中
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(apiKey, apiKey);
                return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
            }

        }
        return null;
    }

    // 验证成功
    @Override
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }

    // 验证失败
    @Override
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }
}
