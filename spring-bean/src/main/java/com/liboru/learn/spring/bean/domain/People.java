package com.liboru.learn.spring.bean.domain;

import com.liboru.learn.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class People implements InitializingBean {

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
    public void afterPropertiesSet() throws Exception {
        this.name = "lisi";
        System.out.println("People afterPropertiesSet...");
    }
}
