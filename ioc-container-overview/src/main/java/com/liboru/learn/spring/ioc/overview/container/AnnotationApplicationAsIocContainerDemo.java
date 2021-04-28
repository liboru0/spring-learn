package com.liboru.learn.spring.ioc.overview.container;

import com.liboru.learn.spring.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AnnotationApplicationAsIocContainerDemo {

    public static void main(String[] args) {
        // 创建 ApplicationContext 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类
        applicationContext.register(AnnotationApplicationAsIocContainerDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        lookupCollectionTypeByType(applicationContext);

    }

    @Bean
    public User user(){
        User user = new User();
        user.setId(1L);
        user.setName("Ming");
        return user;
    }

    private static void lookupCollectionTypeByType(ApplicationContext applicationContext) {
        Map<String, User> users = applicationContext.getBeansOfType(User.class);
        System.out.println(users);
    }

}
