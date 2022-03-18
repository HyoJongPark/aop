package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
/*
지연 조회
callServiceProvider.getObject() 를 호출하는 시점에 스프링 컨테이너에서 빈을 조회한다.
 */
@Slf4j
@Component
public class CallServiceV2 {

//    private final ApplicationContext applicationContext;
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

    public void external() {
        log.info("call external");
//        CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        CallServiceV2 callServiceV2 = callServiceProvider.getObject();
        callServiceV2.internal(); //외부 메서드 호출(callServiceV1.internal()) -> 의존관계 주입을 받은 프록시가 사용됨
    }

    public void internal() {
        log.info("call internal");
    }
}
