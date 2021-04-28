package com.liboru.learn.spring.bean.factory;

import com.liboru.learn.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setId(3L);
        user.setName("factory-bean");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

}
