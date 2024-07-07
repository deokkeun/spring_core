package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택
        // basePackages: 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
        // basePackageClasses: 지정한 클래스의 패키지를 탐색 시작 위로 지정한다.
        // basePackageClasses = AutoAppConfig.class,
        // basePackages = "hello.core.member",
        // 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.

        /**
         * 권장하는 방법
         * 개인적으로 즐겨 사용하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다.
         * 최근 스프링 부트도 이 방법을 기본으로 제공한다.
         *
         * 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication 를 이 프로젝트 시작 루트 위치에 두는 것이 관례이다.
         * 그리고 이 설정안에 바로 @ComponentScan 이 들어있다!
        */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Configuration.class})
)
public class AutoAppConfig {

        /**
         * 수동 등록한 빈과 자동 등록된 빈의 이름이 같은 경우
         * Overriding bean definition for bean 'memoryMemberRepository' with a different
         * definition: replacing
         *
         * 이 경우 수동 빈 등록이 우선권을 가진다.
         * (수동 빈이 자동 빈을 오버라이딩 해버린다.)
         *
         * 수동 빈 등록, 자동 빈 등록 오류 시 스프링 부트 에러
         * Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
         *
         * 스프링 부트인 CoreApplication 을 실행해보면 오류를 볼 수 있다.
         * 해결방안
         * 수동 빈, 자동 빈 둘중 하나를 제거하거나 application.properties에
         * spring.main.allow-bean-definition-overriding=true 를 등록한다.
         */
//        @Bean(name = "memoryMemberRepository")
//        MemberRepository memberRepository() {
//                return new MemoryMemberRepository();
//        }
}
