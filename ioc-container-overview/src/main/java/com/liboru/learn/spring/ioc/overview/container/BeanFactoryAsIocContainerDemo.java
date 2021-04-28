package com.liboru.learn.spring.ioc.overview.container;

import com.liboru.learn.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class BeanFactoryAsIocContainerDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // XML 配置文件 classpath 路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int beanDefinitionCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean定义加载的数量："+beanDefinitionCount);

        lookupCollectionTypeByType(beanFactory);

    }

    private static void lookupCollectionTypeByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println(users);
        }
    }

}
