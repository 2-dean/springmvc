package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * PathVariable (경로변수) 사용
     * 변수명이 같으면 생략가능
     *
     * @PathVariable("userId") String userId -> @PathVariable userId
     * /mapping/{userId}
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId = {}", data);
        return "ok";
    }


    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("userId = {}, orderId={}" , userId, orderId);
        return "ok";
    }


    /** 파라미터 조건 매핑
     * 파라미터로 추가 매핑  (잘 안씀)
     * params="mode"
     * params"!mode"
     *
     * 아래의 경우 해당 파라미터가 없는 경우 에러.
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }


    /**
     * 특정 헤더로 추가매핑
     *
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * 미디어 타입조건 매핑
     * 요청헤더의 컨텐츠 타입
     */
    @PostMapping(value = "mapping-consume", consumes = "application/json")
    public String mappingConsume() {
        log.info("mappingConsume");
        return "ok";
    }
}
