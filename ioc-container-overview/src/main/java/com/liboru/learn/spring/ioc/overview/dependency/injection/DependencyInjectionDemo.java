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
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // 依赖来源一：自定义 Bean
        UserRepository userRepository = applicationContext.getBean("userRepository",UserRepository.class);
        System.out.println(userRepository);

        // 依赖来源二：依赖注入，内建依赖
        whoIsContainer(userRepository,applicationContext);

        ObjectFactory<ApplicationContext> userObjectFactory = userRepository.getObjectFactory();
        System.out.println(userObjectFactory.getObject());
        System.out.println(userObjectFactory.getObject()==applicationContext);

        // 依赖查找（报错）
        //System.out.println(beanFactory.getBean(BeanFactory.class));

        // 依赖来源三：容器内建 Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println(environment);

    }

    private static void whoIsContainer(UserRepository userRepository,ApplicationContext applicationContext){
        /**
         * userRepository.getBeanFactory() 通过类型依赖注入得到的Bean
         *  得到的是 DefaultListableBeanFactory
         *  因为在 AbstractApplicationContext#prepareBeanFactory 方法中
         *      beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
         *      以上代码指定了 BeanFactory 类型的对象是 ApplicationContext#getBeanFactory() 方法的内容，而非它自身。
         */
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory()==applicationContext);
    }


}
