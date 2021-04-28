package com.liboru.learn.spring.bean.definition;

import com.liboru.learn.spring.bean.domain.People;
import com.liboru.learn.spring.bean.factory.DefaultUserFactory;
import com.liboru.learn.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanInitializationDemo.class);

        applicationContext.refresh();
        System.out.println("Spring 上下文已启动...");

        People people = applicationContext.getBean(People.class);
        System.out.println(people);

        System.out.println("Spring 上下文准备关闭...");
        applicationContext.close();
        System.out.println("Spring 上下文已关闭...");

    }

    @Bean(initMethod = "initPeople",destroyMethod = "myDestroy")
    @Lazy
    public People userFactory(){
        return new People();
    }

}
