package hello.proxy;

import hello.proxy.cofig.v1_proxy.ConcreteProxyConfig;
import hello.proxy.cofig.v1_proxy.InterfaceProxyConfig;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


//@Import({AppV1Config.class, AppV2Config.class}) // 해당 클래스를 스프링 빈으로 등록한다.
//@Import(InterfaceProxyConfig.class)
@Import({ConcreteProxyConfig.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //hello.proxy.app package 만 컴포넌트 스캔의 대상이 된다.
// Config 파일을 따로 @Import 를 통해서 스프링 빈으로 등록한 이유는 계속 다른 예제를 만들면서 Config 파일을 바꿔서 등록하기 위함
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace(){
		return new ThreadLocalLogTrace();
	}
}
