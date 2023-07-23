package hello.servlet.basic.controller.v3;

import hello.servlet.basic.controller.v3.ControllerV3;
import hello.servlet.basic.view.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        //로직 로직 로직
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
