package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor // final(필수값) 이 있는 생성자를 만들어준다
public class OrderServiceImpl implements OrderService{

    /**
     * final 키워드
     * 생서앚 주입을 사용하면 필드에 final 키워드를 사용할 수 있다.
     * 그래서 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다.
     *
     * 기억하자! 컴파일 오류는 세상에서 가장 빠르고, 좋은 오류다!
     *
     * 참고: 수정자 주입을 포함한 나머지 주입 방식은 모두 생성자 이후에 호출되므로, 필드에 final 키워드를 사용할 수 없다.
     * 오직 생성자 주입 방식만 final 키워드를 사용할 수 있다.
     */
//    @Autowired
    private final MemberRepository memberRepository;
//    @Autowired
    private final DiscountPolicy discountPolicy;

    /**
     *       수정자 주입
     *      @Autowired의 기본 동작은 주입할 대상이 없으면 오류가 발생한다.
     *      주입할 대상이 없어도 동작하게 하려면 '@Autowired(required = false)'로 지정하면 된다
     */
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    /**
     *      new OrderServiceImpl(memberRepository, discountPolicy);
     *     @Autowired // 생성자가 1개인 경우 생략해도 무관 , 생성자 주입
     *      불변, 누락, final 키워드 사용
     *
     *      @RequiredArgsConstructor
     *      롬복 라이브러리가 제공하는 @RequiredArgsConstructor 기능을 사용하면 final 이 붙은 필드를 모아서
     *      생성자를 자동으로 만들어준다.
     */
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
