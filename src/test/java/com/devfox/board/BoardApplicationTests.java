package com.devfox.board;

import com.devfox.board.util.LevelUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.Reader;

@SpringBootTest
class BoardApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void levelLoadTest() throws IOException {
        LevelUtil levelUtil = new LevelUtil();

        System.out.println(levelUtil.getUserLevel(1));
    }

}
