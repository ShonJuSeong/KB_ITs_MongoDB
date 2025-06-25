package org.scoula.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // /WEB-INF/views/login.jsp로 연결됨 (ViewResolver 설정이 되어 있어야 함)
    }
}