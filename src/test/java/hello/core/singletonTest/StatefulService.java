package hello.core.singletonTest;

public class StatefulService {

    //진짜 공유필드는 조심해야 한다! 스프링 빈은 무상태(stateless)로 설계하자.
//    private int price; // 상태를 유지하는 필드 10000 -> 20000
//
//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기가 문제!
//    }

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
