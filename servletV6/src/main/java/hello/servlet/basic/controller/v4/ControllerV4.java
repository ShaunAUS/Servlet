package hello.servlet.basic.controller.v4;

import java.util.Map;

public interface ControllerV4 {

    //model 객체 넘김
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
