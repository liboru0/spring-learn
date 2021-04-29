package com.liboru.learn.spring.dependency.lookup;

import com.liboru.learn.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全 依赖查找示例
 */
public class TypeSafetyDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDemo.class);

        applicationContext.refresh();

        displayBeanFactoryGetBean(applicationContext);
        displayGetIfAvailable(applicationContext);

        applicationContext.close();
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory){
        try{
            beanFactory.getBean(User.class);
        }catch (BeansException exception){
            exception.printStackTrace();
        }
    }

    private static void displayGetIfAvailable(AnnotationConfigApplicationContext applicationContext){
        try{
            User user = applicationContext.getBeanProvider(User.class).getIfAvailable();
            System.out.println(user);
        }catch (BeansException exception){
            exception.printStackTrace();
        }
    }


}
