package hello.servlet.basic.controller.v5.adapter;

import hello.servlet.basic.controller.v2.ControllerV2;
import hello.servlet.basic.controller.v3.ControllerV3;
import hello.servlet.basic.controller.v5.MyHandlerAdapter;
import hello.servlet.basic.view.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV2;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws ServletException, IOException {

        //frontController에서 이미 supprots() 로 검사했기 때문
        ControllerV2 controller = (ControllerV2) handler;
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
            .forEachRemaining(paramName -> paramMap.put(paramName,
                request.getParameter(paramName)));
        return paramMap;
    }
}

