package hello.core.singletonTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    /**
     *     싱글톤 방식의 주의점!!!!!
     *       싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든, 객체 인스턴스를 하나만 생성해서 공유하는 싱글통 방식은
     *       여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.
     *       무상태(stateless)로 설계해야 한다!
     *           특정 클라이언트에 의존적인 필드가 있으면 안된다.
     *           특정 클라이언트에 값을 변경할 수 있는 필드가 있으면 안된다!
     *           가급적 읽기만 가능해야 한다.
     *           필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
     *       스프링 빈의 필드에 공유 값을 설정하면 정말 큰 장애가 발생할 수 있다!!!
     *
     *       진짜 공유필드는 조심해야 한다! 스프링 빈은 무상태(stateless)로 설계하자.
     */

    @Test
    void statefulServiceSingleton() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = applicationContext.getBean(StatefulService.class);
        StatefulService statefulService2 = applicationContext.getBean(StatefulService.class);

//        //ThreadA: A사용자 10000원 주문
//        statefulService1.order("userA", 10000);
//        //ThreadB: B사용자 20000원 주문
//        statefulService1.order("userB", 20000);

//        //ThreadA: 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

        //ThreadA: A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        int userBPrice = statefulService1.order("userB", 20000);

        System.out.println("userAPrice = " + userAPrice);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}