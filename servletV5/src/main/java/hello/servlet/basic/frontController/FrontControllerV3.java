package hello.servlet.basic.frontController;

import hello.servlet.basic.controller.ControllerV3;
import hello.servlet.basic.controller.MemberFormControllerV2;
import hello.servlet.basic.controller.MemberFormControllerV3;
import hello.servlet.basic.controller.MemberSaveControllerV3;
import hello.servlet.basic.view.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front- controller/v3/*")
public class FrontControllerV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerV3() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(request, response);
        view.render(null, request, response);
    }
}
