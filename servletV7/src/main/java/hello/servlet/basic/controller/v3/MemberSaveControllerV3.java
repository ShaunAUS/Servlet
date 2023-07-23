package hello.servlet.basic.controller.v3;

import hello.servlet.basic.controller.v3.ControllerV3;
import hello.servlet.basic.view.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV3 implements ControllerV3 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

       /* Member createMember = new Member(username, age);
        memberRepository.save(createMember);*/

        // createMember = 실제는 객체
        request.setAttribute("member", "createMember");
        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
