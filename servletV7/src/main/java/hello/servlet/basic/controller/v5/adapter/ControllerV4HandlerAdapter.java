package hello.servlet.basic.controller.v5.adapter;

import hello.servlet.basic.controller.v4.ControllerV4;
import hello.servlet.basic.controller.v5.MyHandlerAdapter;
import hello.servlet.basic.view.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV4;
    }

    @Override

    public ModelView handle(HttpServletRequest request, HttpServletResponse
        response, Object handler) {


        // request -> map , model 넘기기 adapter에서 시작
        ControllerV4 controller = (ControllerV4) handler;
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

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
