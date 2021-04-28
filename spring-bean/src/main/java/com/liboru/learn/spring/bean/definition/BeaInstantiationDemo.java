package com.liboru.learn.spring.bean.definition;

import com.liboru.learn.spring.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeaInstantiationDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");

        User userStaticMethod = applicationContext.getBean("user-by-static-method", User.class);
        System.out.println(userStaticMethod);

        User userInstanceMethod = applicationContext.getBean("user-by-instance-method", User.class);
        System.out.println(userInstanceMethod);

        User userFactoryBean = applicationContext.getBean("user-by-factory-bean", User.class);
        System.out.println(userFactoryBean);

    }

}
