package com.devfox.board.service.user;

import com.devfox.board.model.User;
import com.devfox.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring Security 로그인을 위한 UserDetailsService
 *
 * @author 이진솔
 * @since 2021.11.07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BoardUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     *
     * @param username 유저네임 값을 받아서 UserRepository를 통한 조회 후 로그인 실행
     * @return UserDetail로 설정
     * @throws UsernameNotFoundException 로그인 시 계정이 없으면 UsernameNotFoundException을 발생시킴
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // User의 모든 객체를 조회해서 넘겨줬기때문에 기본적인 값 이외에 principal에서
        // nickname, regdate등 모든 값들을 조회할 수 있다.
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("없는 유저입니다. username : " + username);
        } else {
            return user;
        }

    }

}
