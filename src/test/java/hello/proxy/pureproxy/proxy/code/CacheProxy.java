package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject{

    private Subject target; // 프록시가 호출하는 대상(실제 객체의 참조)
    private String cacheValue;

    public CacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("프록시 호출");
        if(cacheValue == null){ // 첫 호출 시
            cacheValue = target.operation();
        }
        return cacheValue;
    }
}
