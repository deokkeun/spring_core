package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // @ComponentScan 이 내장되어 자동으로 spring bean이 등록된다. (package hello.core;)
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	/**
	 * @Controller: 스프링 MVC 컨트롤러로 인식
	 * @Repository: 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
	 * @Configuration: 앞서 보았듯이 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
	 * @Service: 사실 @Service 는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고
	 * 비즈니스 계층을 인식하는데 도움이 된다.
	 *
	 * 자동 등록되는 빈의 컴포넌트 이름이 중복되는 경우
	 * ConflictingBeanDefinitionException 예외 발생
	 */
}
