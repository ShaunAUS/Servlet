package hello.servlet.basic.controller;

import hello.servlet.basic.view.ModelView;
import java.util.Map;

/**
 * Model
 */
public class MemberFormControllerV2 implements ControllerV2{
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
