package com.devfox.board.controller;

import com.devfox.board.model.Level;
import com.devfox.board.model.Setting;
import com.devfox.board.model.User;
import com.devfox.board.service.user.SettingService;
import com.devfox.board.service.user.UserService;
import com.devfox.board.util.LevelUtil;
import com.devfox.board.util.PageScriptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@ControllerAdvice
@RequiredArgsConstructor
public class PageController {

    private final UserService userService;
    private final SettingService settingService;

    @ModelAttribute
    public void commonAttributes(
            Model model,
            HttpServletResponse response,
            @AuthenticationPrincipal User userAuth
    ) throws IOException {
        if (userAuth == null) {
            PageScriptUtil.alertAndMove(response, "로그인이 필요한 서비스입니다.", "/");
        } else {
            User userInfo = userService.findUser(userAuth.getUsername());
            Setting setting = settingService.loadSetting();
            Level userLevel = LevelUtil.getUserLevel(userInfo.getLevel());


            model.addAttribute("setting", setting);
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("userLevel", userLevel);
        }

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
