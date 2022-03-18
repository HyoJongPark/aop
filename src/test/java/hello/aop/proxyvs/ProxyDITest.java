package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ProxyDIAspect.class)
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl; //JDK 프록시의 경우 구체 타입으로 DI가 불가능 하다.(JDK 프록시가 인터페이스에 의존하기 때문에)

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());

        memberServiceImpl.hello("hello");
    }
}
