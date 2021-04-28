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

        People people = applicationContext.getBean(People.class);
        System.out.println(people);

        applicationContext.close();

    }

    @Bean(initMethod = "initPeople")
    @Lazy
    public People userFactory(){
        return new People();
    }

}
