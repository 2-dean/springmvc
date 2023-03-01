package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller //@controller 면서 Stirng을 반환하는 controller 는 view를 찾아서 반환하게됨
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }


    @ResponseBody // @RestController 와 같은 효과 http 응답에 return 값이 보내짐
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){
        log.info("memberName={}, memberAge={}", memberName, memberAge);

        return "ok";
    }

    @ResponseBody // @RestController 와 같은 효과 http 응답에 return 값이 보내짐
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){
        log.info("username={}, age={}", username, age);

        return "ok";
    }


    @ResponseBody // @RestController 와 같은 효과 http 응답에 return 값이 보내짐
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){ // 요청 파라미터 명과 맞으면 다 생략가넝
        log.info("V4 username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody // @RestController 와 같은 효과 http 응답에 return 값이 보내짐
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam (required = true) String username,
            @RequestParam (required = false) Integer age){
        //@RequestParam (required = true) true = 반드시 있어야함, false는 없어도됨
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam (required = true, defaultValue = "guest") String username,
            @RequestParam (required = false, defaultValue = "-1") Integer age){

        // defaultValue 는 "" 빈 문자열도 처리해줌 ㅋtPara
        //Integer는 객체형이라서 null 입력가능.! int로 했을경우 파라미터 입력안해주면 오류!!
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){ //@ModelAttribute 애노테이션 생략가능
        // 스피링은 생략시 string, , int, Integer 같은 단순타입은 @RequestParam , 나머지는 @ModelAttribute 로 적용함
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";
    }
}
