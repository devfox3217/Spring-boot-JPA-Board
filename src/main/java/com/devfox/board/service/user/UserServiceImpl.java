package com.devfox.board.service.user;

import com.devfox.board.model.User;
import com.devfox.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public boolean save(User user) {
        User result = userRepository.save(user);
        return result != null;
    }

    @Override
    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }

}