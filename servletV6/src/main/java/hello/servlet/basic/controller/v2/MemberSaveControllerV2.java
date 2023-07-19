package hello.servlet.basic.controller.v2;

import hello.servlet.basic.controller.v2.ControllerV2;
import hello.servlet.basic.view.ModelView;
import java.util.Map;

public class MemberSaveControllerV2 implements ControllerV2 {

    @Override
    public ModelView process(Map<String, String> paramMap) {

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        /* Member createMember = new Member(username, age);
        memberRepository.save(createMember);*/

        ModelView modelView = new ModelView("save-result");


        //createMember = 실제로는 객체
        modelView.getModel().put("member", "createMember");

        //model + view
        //view에 map으로 데이터 담아서 보낸다!!
        return modelView;
    }
}
