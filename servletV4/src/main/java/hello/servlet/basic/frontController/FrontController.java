package hello.servlet.basic.frontController;

import hello.servlet.basic.controller.ControllerV1;
import hello.servlet.basic.controller.TestController;
import hello.servlet.basic.controller.TestControllerV2;
import hello.servlet.basic.controller.TestControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front- controller/v1/*")
public class FrontController extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontController() {
        controllerMap.put("/front-controller/v1/members/new-form", new TestController());
        controllerMap.put("/front-controller/v1/members/save-result", new TestControllerV2());
        controllerMap.put("/front-controller/v1/members", new TestControllerV3());
    }

    //request에서 요청 URI를 꺼내서 해당 URI에 맞는 Controller를 찾아서 호출
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        System.out.println("FrontControllerServletV1.service");
        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response);
    }

}
