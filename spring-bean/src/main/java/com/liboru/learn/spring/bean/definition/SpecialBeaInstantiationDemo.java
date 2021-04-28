package com.liboru.learn.spring.bean.definition;

import com.liboru.learn.spring.bean.factory.UserFactory;
import com.liboru.learn.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpecialBeaInstantiationDemo {

    public static void main(String[] args) {

        // ServiceLoader
        demoServiceLoader();

        // ServiceLoaderFactoryBean
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-special-instantiation-context.xml");
        ServiceLoader<UserFactory> serviceLoader = applicationContext.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);

        // AutowireCapableBeanFactory
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        User user = autowireCapableBeanFactory.createBean(User.class);
        System.out.println(user);

    }

    public static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader){
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory next = iterator.next();
            System.out.println(next.createUser());
        }
    }

}
