package com.devfox.board.service.user;

import com.devfox.board.model.User;
import com.devfox.board.repository.UserRepository;
import com.devfox.board.util.LevelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public boolean save(User user) throws IOException {
        user.setPoint(LevelUtil.checkUserLevel(user));
        User result = userRepository.save(user);
        return result != null;
    }

    @Override
    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }

}
