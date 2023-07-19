package hello.servlet.basic.controller.v5;

import hello.servlet.basic.view.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    //interface controller 넘어왔을때 처리 가능한지 판단용
    boolean supports(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws ServletException, IOException;

}
