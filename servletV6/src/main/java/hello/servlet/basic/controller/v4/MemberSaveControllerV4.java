package hello.servlet.basic.controller.v4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4{

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));
/*
        Member member = new Member(username, age);
        memberRepository.save(member);*/

        //두번쨰 member 는 객체
        model.put("member", "member");
        return "save-result";
    }
}
