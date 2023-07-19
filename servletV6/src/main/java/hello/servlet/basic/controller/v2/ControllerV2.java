package hello.servlet.basic.controller.v2;

import hello.servlet.basic.view.ModelView;
import java.util.Map;

/**
 * ModelView
 */
public interface ControllerV2 {
    ModelView process(Map<String, String> paramMap);
}
