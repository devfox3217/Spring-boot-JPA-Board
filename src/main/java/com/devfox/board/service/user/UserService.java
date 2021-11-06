package com.devfox.board.service.user;

import com.devfox.board.model.User;

public interface UserService {
    boolean save(User user);
    User findUser(String username);
}
