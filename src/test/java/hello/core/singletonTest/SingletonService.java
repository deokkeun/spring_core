package hello.core.singletonTest;

public class SingletonService {

    // 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 선택 했다.
    /**
     * 싱글톤 패턴 문제점
     * 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
     * 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP를 위반한다.
     * 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
     * 테스트하기 어렵다.
     * 내부 속성을 변경하거나 초기화 하기 어렵다
     * private 생성자로 자식 클래스를 만들기 어렵다.
     * 결론적으로 유연성이 떨어진다.
     * 안티패턴으로 불리기도 한다.
     */

    //1. static 영역에 객체를 딱 1개만 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
