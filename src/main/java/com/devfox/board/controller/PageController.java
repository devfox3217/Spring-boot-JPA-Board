package com.devfox.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @ModelAttribute
    public void commonAttributes(
            Model model
    ) {
    }

    @RequestMapping("/main")
    public String main() {

        return "main";
    }

    @RequestMapping("/board")
    public String board() {
        return "board";
    }
}
