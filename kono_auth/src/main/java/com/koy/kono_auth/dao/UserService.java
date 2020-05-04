package com.koy.kono_auth.dao;

/**
 * @Description
 * @Auther Koy  https://github.com/Koooooo-7
 * @Date 2020/05/04
 */
public interface UserService{

/**
 * 校验App权限
 */

  boolean authenticate(String appName, String apiKey);
}
