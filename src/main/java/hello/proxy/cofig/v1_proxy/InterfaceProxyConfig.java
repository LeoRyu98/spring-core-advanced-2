package hello.proxy.cofig.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.cofig.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.cofig.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.cofig.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig { // 프록시를 실제 스프링 빈 대신 등록

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace){
        OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(serviceImpl, logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1Impl orderRepository = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(new OrderRepositoryV1Impl(), logTrace);
    }
}
