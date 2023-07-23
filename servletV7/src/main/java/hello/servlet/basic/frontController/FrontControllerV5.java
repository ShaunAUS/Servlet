package hello.servlet.basic.frontController;

import hello.servlet.basic.controller.v2.ControllerV2;
import hello.servlet.basic.controller.v3.MemberFormControllerV3;
import hello.servlet.basic.controller.v3.MemberSaveControllerV3;
import hello.servlet.basic.controller.v4.MemberFormControllerV4;
import hello.servlet.basic.controller.v4.MemberSaveControllerV4;
import hello.servlet.basic.controller.v5.MyHandlerAdapter;
import hello.servlet.basic.controller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.basic.controller.v5.adapter.ControllerV4HandlerAdapter;
import hello.servlet.basic.view.ModelView;
import hello.servlet.basic.view.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 어댑터 적용 프론트 컨트룰러
 */
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front- controller/v5/*")
public class FrontControllerV5 extends HttpServlet {

    //private Map<String, ControllerV2> controllerMap = new HashMap<>();

    //아무 컨트룰러나 들어올수 있게 Object
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();


    public FrontControllerV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    //핸들러 목록
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form",
            new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());

        //V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form",
            new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());

    }


    /**
     * 1. url에 해당하는 컨트룰러(핸들러) 가져온다
     * 2. 컨트룰러에 맞는 어댑터 가져옴
     * 3. 어댑터로 컨트룰러 실행 -> ModelView 반환
     * 4. modelView 0> myView -> view render
     *
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // handler = controller 얻어온다
        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //컨트룰러에 맞는 어댑터 가져옴
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(request, response, handler);
        MyView view = viewResolver(mv.getViewName());
        view.render(mv.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    //핸들러 어댑터 목록에서 핸들러(컨트룰러)를 찾아온다
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}


