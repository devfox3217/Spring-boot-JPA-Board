package com.devfox.board.controller;

import com.devfox.board.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PageController {

    @RequestMapping(value = {"/", "/index"})
    public String index(
            @AuthenticationPrincipal User user
    ) {
        if (user != null) {
            return "redirect:main";
        } else {
            return "index";
        }
    }

    @RequestMapping("/signin")
    public String signin(
            @AuthenticationPrincipal User user
    ) {
        if (user != null) {
            return "redirect:main";
        } else {
            return "signin";
        }

    }

    @RequestMapping("/main")
    public String main() {

        return "main";
    }
}
