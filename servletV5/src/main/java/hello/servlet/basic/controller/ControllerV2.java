package hello.servlet.basic.controller;

import hello.servlet.basic.view.ModelView;
import java.util.Map;

/**
 * ModelView
 */
public interface ControllerV2 {
    ModelView process(Map<String, String> paramMap);
}
