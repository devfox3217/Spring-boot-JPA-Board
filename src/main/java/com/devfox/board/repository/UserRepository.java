package com.devfox.board.repository;

import com.devfox.board.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     *
     * @param username
     * @see com.devfox.board.service.user.UserService
     */
    User findByUsername(String username);
}
