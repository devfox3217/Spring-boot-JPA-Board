package com.devfox.board.controller;

import com.devfox.board.service.user.BoardUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final BoardUserDetailsService boardUserDetailsService;



}
