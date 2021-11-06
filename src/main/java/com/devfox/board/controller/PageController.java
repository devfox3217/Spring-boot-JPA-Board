package com.devfox.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class PageController {

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/signin")
    public String signin() {
        return "signin";
    }
}
