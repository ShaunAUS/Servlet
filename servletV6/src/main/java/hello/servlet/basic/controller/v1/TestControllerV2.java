package hello.servlet.basic.controller.v1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestControllerV2 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        //save 로직

        //save 로직이 끝난뒤 해당 데이터를 request에 저장한뒤 view로 이동
        request.setAttribute("data", "hello!");

        String viewPath = "/WEB-INF/views/save-result.jsp";

        //view로 forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }
}
