package hello.servlet.basic.frontController;

import hello.servlet.basic.controller.v4.ControllerV4;
import hello.servlet.basic.controller.v4.MemberFormControllerV4;
import hello.servlet.basic.controller.v4.MemberSaveControllerV4;
import hello.servlet.basic.view.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //httpReuqest  ->map으로 정리해서 컨트룰러로 넘김
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>(); //추가

        // 모델 객체를 프론트컨트룰러에서 넘겨줌
        String viewName = controller.process(paramMap, model);
        MyView view = viewResolver(viewName);
        view.render(model, request, response);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()

            .forEachRemaining(paramName -> paramMap.put(paramName,
                request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
