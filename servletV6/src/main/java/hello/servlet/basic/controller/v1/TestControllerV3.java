package hello.servlet.basic.controller.v1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestControllerV3 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // 다른 로직

        String viewPath = "/WEB-INF/views/다른페이지 경로.jsp";

        //view로 forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
