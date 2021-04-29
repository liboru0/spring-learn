package com.liboru.learn.spring.dependency.lookup;

import com.liboru.learn.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class ObjectProviderDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);

        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);

        applicationContext.close();

    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        /*Iterable<String> iterable = objectProvider;
        for (String s : iterable) {
            System.out.println(s);
        }*/
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        User user = beanProvider.getIfAvailable(() -> {
            User userIn = new User();
            userIn.setName("default");
            return userIn;
        });
        System.out.println(user);
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        String str = objectProvider.getObject();
        System.out.println(str);
    }

    @Bean
    @Primary
    public String helloWorld(){
        return "Hello,world";
    }

    @Bean
    public String message(){
        return "Message";
    }

}
