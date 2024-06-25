package hello.core.singletonTest;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        /**
         * @Bean 사용 시 프록시가 적용되지 않는 static 메서드는 컨테이너에 동일한 빈이 존재하는지에 대한
         * 로직이 수행되지 않기 때문에 싱글톤을 보장하지 않는다.
         */

        MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = applicationContext.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    /**
     * @Configuration을 적용하면( bean = class hello.core.AppConfig$$SpringCGLIB$$0 )
     *   Appconfig를 상속받은 SpringCGLIB (바이트 코드 조작 라이브러리)로 임의의 다른 클래스를 만들고,
     *      그 다른 클래스를 스프링 빈으로 등록한 것
     *      이로인해 @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고,
     *      스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.(싱글톤 보장)
     *
     * @Configuration을 적용하지 않으면 ( bean = class hello.core.AppConfig )
     * CGLIB 기술을 사용하지 않고 순수한 클래스로 등록되어 스프링 컨테이너에서 관리하지 않고,
     * 호출되는 빈이 모두 등록된다.(싱글톤 보장 X)
     */
    @Test
    void configurationDeep() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = applicationContext.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
