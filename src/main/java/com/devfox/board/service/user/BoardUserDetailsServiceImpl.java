package com.devfox.board.service.user;

import com.devfox.board.model.User;
import com.devfox.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardUserDetailsServiceImpl implements UserDetailsService, BoardUserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("없는 유저입니다. username : " + username);
        } else {
            return user;
        }

    }

    @Override
    public boolean save(User user) {
        User result = userRepository.save(user);
        return result != null;
    }




}
