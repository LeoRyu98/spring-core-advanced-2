package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping // 스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러로 인식(HTTP URL 매핑되고 동작)
@ResponseBody
public interface OrderControllerV1 {

    @GetMapping("v1/request")
    String request(@RequestParam("itemId") String itemId);
    // 인터페이스에서는 실행 시점에 요청 파라미터의 인식이 잘 안될 수 있다.
    // 그러므로 인터페이스에서는 @RequestParam 를 사용하자.

    @GetMapping("/v1/no-log")
    String noLog();
}
