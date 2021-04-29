package com.liboru.learn.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class ObjectProviderDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);

        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);

        applicationContext.close();

    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        String str = objectProvider.getObject();
        System.out.println(str);
    }

    @Bean
    public String helloWorld(){
        return "Hello,world";
    }

}
