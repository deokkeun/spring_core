package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//    GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");

//    BeanDefinition -> ApplicationContext -> AnnotationConfigApplicationContext, GenericXmlApplicationContext ...
//    스프링 빈 설정 메타 정보 - BeanDefinition

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        " beanDefinition = " + beanDefinition);
            }
        }

        /**
         * BeanDefinition 정보
         * BeanClassName: 생성할 빈의 클래스 명(자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음)
         * factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름, 예) appConfig
         * factoryMethodName: 빈을 생성할 팩토리 메서드 지정, 예) memberService
         * Scope: 싱글톤(기본값)
         * lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때 까지 최대한 생성을 지연처리 하는지 여부
         * InitMethodName: 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명
         * DestroyMethodName: 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명
         * Constructor arguments, Properties: 의존관계 주입에서 사용한다.(자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음)
         */
    }
}
