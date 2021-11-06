package com.devfox.board.service.user;

import com.devfox.board.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface BoardUserDetailsService {
    UserDetails loadUserByUsername(String username);

    boolean save(User user);
}
