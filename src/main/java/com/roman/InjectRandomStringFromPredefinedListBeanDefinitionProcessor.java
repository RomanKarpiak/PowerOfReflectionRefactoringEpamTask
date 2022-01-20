package com.roman;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InjectRandomStringFromPredefinedListBeanDefinitionProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectRandomString annotation = field.getAnnotation(InjectRandomString.class);
            if (annotation != null) {
                List messages = new ArrayList<>();
                String fieldName = annotation.fieldName();
                try {
                    Field fieldWithList = bean.getClass().getDeclaredField(fieldName);
                    fieldWithList.setAccessible(true);
                    messages = (List) fieldWithList.get(bean);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                Collections.shuffle(messages);
                String row = (String) messages.get(0);
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, row);
            }

        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
