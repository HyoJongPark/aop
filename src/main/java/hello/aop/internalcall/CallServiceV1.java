package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
자기 자신을 setter 주입 -> 프록시 객체가 주입된다.
 */
@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("callServiceV1 setter={}", callServiceV1.getClass());//프록시 주입
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal(); //외부 메서드 호출(callServiceV1.internal()) -> 의존관계 주입을 받은 프록시가 사용됨
    }

    public void internal() {
        log.info("call internal");
    }
}
