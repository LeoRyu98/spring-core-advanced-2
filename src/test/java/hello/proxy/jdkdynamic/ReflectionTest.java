package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    public void reflection0() throws Exception {
        Hello target = new Hello();

        // 공통 로직1 시작
        log.info("start");
        String result1 = target.callA(); // 호출하는 메서드가 다름
        log.info("result={}", result1);
        // 공통 로직1 종료

        // 공통 로직2 시작
        log.info("start");
        String result2 = target.callB(); // 호출하는 메서드가 다름
        log.info("result={}", result2);
        // 공통 로직2 종료
    }

    @Test
    public void reflection1() throws Exception {
        // 클래스의 메타정보 획득 (내부 클래스는 구분을 위해 '$' 사용)
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA"); // 해당 클래스의 'call' 메서드 메타정보를 확득
        Object result1 = methodCallA.invoke(target); // 획득한 메서드 메타정보로 실제 인스턴스의 메서드를 호출
        log.info("result={}", result1);

        //callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result={}", result2);
    }

    @Test
    public void reflection2() throws Exception {
        // 클래스의 메타정보 획득 (내부 클래스는 구분을 위해 '$' 사용)
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    /**
     * 공통 로직1, 공통 로직2를 한 번에 처리할 수 있는 통합된 공통 처리 로직
     * @param method 호출할 메서드 정보를 동적으로 제공
     * @param target 실제 실행할 인스턴스 정보
     * @throws Exception method.invoke(target)를 사용할 때 호출할 클래스와 메서드 정보가 다르면 예외 발생
     */
    private void dynamicCall(Method method, Object target) throws Exception{
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello{
        public String callA(){
            log.info("callA");
            return "A";
        }

        public String callB(){
            log.info("callB");
            return "B";
        }
    }
}
