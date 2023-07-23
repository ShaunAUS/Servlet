package hello.servlet.basic.controller.v3;

import hello.servlet.basic.view.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * View
 */
public interface ControllerV3 {

    MyView process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException;
}
