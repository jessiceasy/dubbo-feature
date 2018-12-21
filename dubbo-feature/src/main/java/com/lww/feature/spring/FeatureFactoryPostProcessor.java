package com.lww.feature.spring;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.remoting.zookeeper.zkclient.ZkClientWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

/**
 * author: liweiwei
 * Date: 2018/12/19
 */
public class FeatureFactoryPostProcessor implements BeanFactoryPostProcessor {
    Logger logger = LoggerFactory.getLogger(FeatureFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanStrs = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanStrs) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (null != beanDefinition
                    && beanDefinition instanceof AbstractBeanDefinition
                    && ((AbstractBeanDefinition) beanDefinition).hasBeanClass()
                    && ((AbstractBeanDefinition) beanDefinition).getBeanClass().equals(ReferenceBean.class)) {
                //<dubbo:reference>中有默认的属性所以m不可能为空
                MutablePropertyValues m = beanDefinition.getPropertyValues();
                m.addPropertyValue(Constants.LOADBALANCE_KEY,"featureLoadBalance");
                System.out.println("================");
            }
        }
    }
}
