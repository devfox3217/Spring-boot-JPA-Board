package com.devfox.board.controller;

import com.devfox.board.model.User;
import com.devfox.board.service.user.UserService;
import com.devfox.board.util.PageScriptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public void insertUser(
            HttpServletResponse response,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String name
    ) throws IOException {
        User userCheck = userService.findUser(username);
        if (userCheck != null) {
            PageScriptUtil.alertAndBack(response, "이미 등록된 유저입니다.");
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            User user = new User();
            user.setUsername(username);
            user.setPassword(encoder.encode(password));
            user.setName(name);
            user.setRoles("USER");
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);

            boolean result = userService.save(user);
            if (result) {
                PageScriptUtil.alertAndMove(response, name + "님, 회원이 되신것을 환영합니다.", "/main");
            } else {
                PageScriptUtil.alertAndBack(response, "회원가입에 실패하였습니다. 다시 시도해주세요");
            }
        }

    }

}
