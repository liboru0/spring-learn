package com.liboru.learn.spring.bean.definition;

import com.liboru.learn.spring.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-definition-context.xml");
        User userZhang = applicationContext.getBean("zhangsan-user", User.class);
        System.out.println(userZhang);
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
        System.out.println(userZhang == user);
    }

}
