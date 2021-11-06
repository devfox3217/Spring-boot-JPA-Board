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

/**
 * 회원과 권한관리를 위한 컨트롤러
 * @author 이진솔
 * @since 2021.11.07
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    /**
     * 회원가입을 위한 컨트롤러 메서드
     * 중복체크를 한 뒤 새로운 User를 생성 후 값을 넣어 저장
     * 최초 가입시 모든 boolean 타입의 값은 true
     *
     * @param response the response
     * @param username 이메일 형식으로 된 username을 입력받음
     * @param password 숫자1 특수기호1 포함한 최소 9자리 이상의 비밀번호
     * @param name     이용자의 이름
     * @return void 로 설정한 후 PageScriptUtil로 화면이동을 제어함
     * @throws IOException PageScriptUtil에 사용되는 PrintWriter를 고려한 IOException
     * @see PageScriptUtil
     */
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public void insertUser(
            HttpServletResponse response,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String name
    ) throws IOException {
        // 최초 username(email)로 중복체크
        User userCheck = userService.findUser(username);
        if (userCheck != null) {
            PageScriptUtil.alertAndBack(response, "이미 등록된 유저입니다.");
        } else {
            // 비밀번호에 사용되는 암호화 모듈 선언
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

            // 저장 후 boolean타입으로 결과 리턴
            boolean result = userService.save(user);
            if (result) {
                // 저장 성공시 이름을 포함한 알림 후 main 페이지로 이동
                PageScriptUtil.alertAndMove(response, name + "님, 회원이 되신것을 환영합니다.", "/main");
            } else {
                // 저장 실패시 다시 원래 페이지로 이동
                PageScriptUtil.alertAndBack(response, "회원가입에 실패하였습니다. 다시 시도해주세요");
            }
        }

    }

}
