package com.devfox.board.service.user;

import com.devfox.board.model.User;

import java.io.IOException;

/**
 * 유저 서비스 인터페이스
 * @author 이진솔
 * @since 2021.11.05
 */
public interface UserService {
    // 유저 정보 저장 및 부분수정
    boolean save(User user) throws IOException;
    // 이메일로 찾기
    User findUser(String username);
}
