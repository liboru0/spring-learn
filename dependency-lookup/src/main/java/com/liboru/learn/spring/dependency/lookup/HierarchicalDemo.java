package com.liboru.learn.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找Demo
 */
public class HierarchicalDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDemo.class);

        // HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前 BeanFactory 的 Parent BeanFactory："+beanFactory.getParentBeanFactory());

        ConfigurableListableBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前 BeanFactory 的 Parent BeanFactory："+beanFactory.getParentBeanFactory());

        applicationContext.refresh();

        displayContainsLocalBean(beanFactory,"user");
        displayContainsLocalBean(parentBeanFactory,"user");
        displayContainsBean(parentBeanFactory,"user");

        applicationContext.close();

    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory,String beanName){
        System.out.printf("双亲委派 BeanFactory[%s] 是否包含 bean[name : %s] : %s\n",beanFactory,beanName
                ,containsBean(beanFactory,beanName));
    }

    /**
     * 类似于 org.springframework.beans.factory.support.AbstractBeanFactory#containsBean
     */
    private static boolean containsBean(HierarchicalBeanFactory beanFactory,String beanName){
        if(beanFactory.containsBean(beanName)){
            return true;
        }
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if(parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory parent = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            return containsBean(parent,beanName);
        }
        return false;
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory,String beanName){
        System.out.printf("当前 BeanFactory[%s] 是否包含 bean[name : %s] : %s\n",beanFactory,beanName
                ,beanFactory.containsLocalBean(beanName));
    }

    private static ConfigurableListableBeanFactory createParentBeanFactory(){
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // XML 配置文件 classpath 路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }

}
