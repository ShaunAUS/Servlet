package hello.servlet.basic.frontController;

import hello.servlet.basic.controller.v2.ControllerV2;
import hello.servlet.basic.controller.v2.MemberFormControllerV2;
import hello.servlet.basic.controller.v2.MemberSaveControllerV2;
import hello.servlet.basic.view.ModelView;
import hello.servlet.basic.view.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * FrontController에서 request 정리해서 map으로 Controller에 넘겨줌
 */
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front- controller/v2/*")
public class FronControllerV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FronControllerV2() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        //URI를 통해 컨트롤러를 찾아온다.
        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);
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
