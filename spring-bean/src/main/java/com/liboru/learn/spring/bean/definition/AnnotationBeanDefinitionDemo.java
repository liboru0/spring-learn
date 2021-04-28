package com.liboru.learn.spring.bean.definition;

import com.liboru.learn.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

// 3. 通过 @Import 方式
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AnnotationBeanDefinitionDemo.class);

        registerUserBeanDefinition(applicationContext,"xiaoming-user");
        registerUserBeanDefinition(applicationContext);

        applicationContext.refresh();

        System.out.println("configBeans:" + applicationContext.getBeansOfType(Config.class));
        System.out.println("users:" + applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }

    /**
     * 命名 Bean 的注册方式
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("id", 1)
                .addPropertyValue("name", "xiaoming");
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        if (StringUtils.hasText(beanName)) {
            // 命名 Bean 的注册方式
            registry.registerBeanDefinition(beanName, beanDefinition);
        } else {
            // 非命名 Bean 的注册方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition,registry);
        }
    }

    /**
     * 非命名 Bean 的注册方式
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    // 2. 通过 @Component 方式
    @Component
    public static class Config {

        // 1. 通过 @Bean 方式定义
        @Bean(name = {"user", "xiaoli-user"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("xiaoli");
            return user;
        }

    }

}
