package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass()); // 현재 클래스 지정

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        // 로그의 레벨을 정할 수 있음
        // log.info("info log={}" + name);  이렇게 쓰면 안됨

        // 안나옴
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);

        //로그찍
        log.info("info log={}",name);
        log.warn("warn log={}",name);
        log.error("error log={}",name);

        return "ok";
    }

}
