package hello.core.scan.filter;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = applicationContext.getBean(BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            // type = FilterType.ANNOTATION 는 기본값이라 생략 가능
//            includeFilters = @Filter(classes = MyIncludeComponent.class),
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            //includeFilters에 MyIncludeComponent 애노테이션을 추가해서 BeanA가 스프링 빈에 등록된다.
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
            /**
             * excludeFilters = {
             *     @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
             *     @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
             * }
            */


            //excludeFilters에 MyExcludeComponent 애노테이션을 추가해서 BeanB가 스프링 빈에 등록되지 않는다.
            /**
             * FilterType 옵션
             * FilterType은 5가지 옵션이 있다.
             *
             * ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.
             *  ex) org.example.SomeAnnotation
             * ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
             *  ex) org.example.SomeClass
             * ASPECTJ: AspectJ 패턴 사용
             *  ex) org.example..*Service+
             * REGEX: 정규 표현식
             *  ex) org\.example\.Default.*
             * CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리
             *  ex) org.example.MyTypeFilter
            */
    )
    static class ComponentFilterAppConfig {

    }
}
