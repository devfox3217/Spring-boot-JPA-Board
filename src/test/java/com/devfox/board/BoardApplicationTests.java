package com.devfox.board;

import com.devfox.board.model.User;
import com.devfox.board.repository.UserRepository;
import com.devfox.board.service.user.UserService;
import com.devfox.board.util.LevelUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Reader;

@SpringBootTest
class BoardApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

}
