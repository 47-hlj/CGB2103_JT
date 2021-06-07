package com.jt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //我是一个配置类
public class HelloConfig {
    class  User {

    }
    /*<bean id="user" class="com.jt.pojo.User"></bean>*/
    /*@bean注解作用:  将方法的返回值交给spring容器进行管理 方法名当作key,
    对象当作value交给spring容器管理*/
    @Bean
    public User user(){

        return new User();
    }
}
