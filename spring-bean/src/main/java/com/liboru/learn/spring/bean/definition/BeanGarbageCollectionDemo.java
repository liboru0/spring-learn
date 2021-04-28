package com.liboru.learn.spring.bean.definition;

import com.liboru.learn.spring.bean.domain.People;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanInitializationAndDestroyDemo.class);

        applicationContext.refresh();

        People people = applicationContext.getBean(People.class);

        System.out.println("Spring 上下文准备关闭...");
        applicationContext.close();
        System.out.println("Spring 上下文已关闭...");
        people = null;

        System.gc();
        Thread.sleep(1000L);

    }

}
