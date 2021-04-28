package com.liboru.learn.spring.bean.factory;

import com.liboru.learn.spring.ioc.overview.domain.User;

public interface UserFactory {

    default User createUser(){
        User user = new User();
        user.setId(2L);
        user.setName("instance-method");
        return user;
    }

}
