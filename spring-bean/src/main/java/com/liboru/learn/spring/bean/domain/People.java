package com.liboru.learn.spring.bean.domain;

import com.liboru.learn.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class People implements InitializingBean, DisposableBean {

    private Long id;

    private String name;

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("static-method");
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @PostConstruct
    public void postConstruct(){
        this.id = 1L;
        System.out.println("People postConstruct...");
    }

    public void initPeople(){
        this.id = 2L;
        System.out.println("People init...");
    }

    @Override
    public void afterPropertiesSet() {
        this.name = "lisi";
        System.out.println("People afterPropertiesSet...");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("People preDestroy...");
    }

    @Override
    public void destroy() {
        System.out.println("People destroy...");
    }

    public void myDestroy() {
        System.out.println("People myDestroy...");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("people finalize...");
    }
}
