package com.devfox.board.controller;

import com.devfox.board.model.User;
import com.devfox.board.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@ControllerAdvice
@RequiredArgsConstructor
public class PageController {

    private final UserService userService;

    @ModelAttribute
    public void commonAttributes(
            Model model,
            @AuthenticationPrincipal User userAuth
    ) {
        User userInfo = userService.findUser(userAuth.getUsername());

        model.addAttribute("userInfo", userInfo);

    }

    @RequestMapping("/main")
    public String main() {

        return "main";
    }

    @RequestMapping("/board")
    public String board() {
        System.out.println("방가");
        return "board";
    }
}
