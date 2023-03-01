package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 *  {"username": "hello", "age": 20}
 *  content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);//json을 변환!!
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok!!!");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);//json을 변환!!
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok!";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")   // requestbody에 직접 만든 객체 지정 가능 http messageConverter가 우리가원하는 문자나 객체등으로 변환해줌(objectMapper.readValue 대신처린) ..
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
                                    //@RequestBody 생략할 경우 ModelAttribute가 붙어벌임 body를 받아야하는데 요청 파라미터를 꺼내버리는..
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok!>_<";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> data) throws IOException {
        HelloData helloData = data.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok!>_<";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) throws IOException {
        // json -> HelloData 객체 -> json으로 변환해서 Http Body에 담아서 보내줌
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data;
    }

}
