package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    public void cglib() throws Exception {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); // CGLIB 는 Enhancer 를 사용해서 프록시를 생성
        enhancer.setSuperclass(ConcreteService.class); // 상속 받을 구체 클래스 지정
        enhancer.setCallback(new TimeMethodInterceptor(target)); // 프록시에 적용할 실행 로직 할당(동적 프록시 클래스 지정)
        ConcreteService proxy = (ConcreteService) enhancer.create(); // 지정 구체 클래스를 상속(extends)해서 만들기 때문에 캐스팅 가능

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }
}
