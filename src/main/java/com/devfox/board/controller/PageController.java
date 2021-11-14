package com.devfox.board.controller;

import com.devfox.board.model.Level;
import com.devfox.board.model.Setting;
import com.devfox.board.model.User;
import com.devfox.board.service.user.PointService;
import com.devfox.board.service.setting.SettingService;
import com.devfox.board.service.user.UserService;
import com.devfox.board.util.LevelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@Controller
@ControllerAdvice
@RequiredArgsConstructor
public class PageController {

    private final UserService userService;
    private final PointService pointService;
    private final SettingService settingService;

    @ModelAttribute
    private void commonAttributes(
            Model model,
            @AuthenticationPrincipal User userAuth
    ) throws IOException, NullPointerException {
        if (userAuth != null) {
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

        return "board";
    }

    @RequestMapping("/content")
    public String content(
            @AuthenticationPrincipal User userAuth
    ) {
        pointService.contentReadPointInsert(userAuth.getUsername());
        return "content";
    }

}
