package com.liboru.learn.spring.ioc.overview.dependency.injection;

import com.liboru.learn.spring.ioc.overview.domain.User;
import com.liboru.learn.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入示例
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        UserRepository userRepository = beanFactory.getBean("userRepository",UserRepository.class);
        System.out.println(userRepository);
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory()==beanFactory);

        ObjectFactory<ApplicationContext> userObjectFactory = userRepository.getObjectFactory();
        System.out.println(userObjectFactory.getObject());
        System.out.println(userObjectFactory.getObject()==beanFactory);

    }


}
