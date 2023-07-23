package hello.servlet.basic.controller.v2;

import hello.servlet.basic.controller.v2.ControllerV2;
import hello.servlet.basic.view.ModelView;
import java.util.Map;

/**
 * Model
 */
public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
