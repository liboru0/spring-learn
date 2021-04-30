package com.liboru.learn.spring.bean.lifecycle;

import com.liboru.learn.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanMetadataConfigurationXmlDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // XML 配置文件 classpath 路径
        String location = "classpath:/META-INF/bean-metadata-config.xml";
        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(location);

        System.out.println(beanFactory.getBean(User.class));
    }

}
