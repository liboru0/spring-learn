package com.liboru.learn.spring.ioc.overview.dependency.injection;

import com.liboru.learn.spring.ioc.overview.domain.User;
import com.liboru.learn.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入示例
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // 依赖来源一：自定义 Bean
        UserRepository userRepository = beanFactory.getBean("userRepository",UserRepository.class);
        System.out.println(userRepository);

        // 依赖来源二：依赖注入，内建依赖
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory()==beanFactory);

        ObjectFactory<ApplicationContext> userObjectFactory = userRepository.getObjectFactory();
        System.out.println(userObjectFactory.getObject());
        System.out.println(userObjectFactory.getObject()==beanFactory);

        // 依赖查找（报错）
        //System.out.println(beanFactory.getBean(BeanFactory.class));

        // 依赖来源三：容器内建 Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);

    }


}
