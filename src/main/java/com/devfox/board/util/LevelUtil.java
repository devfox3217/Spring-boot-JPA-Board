package com.devfox.board.util;

import com.devfox.board.model.Level;
import com.devfox.board.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

public class LevelUtil {

    public static Level getUserLevel(int level) throws IOException {
        Level levelSource = new Level();

        // level.json 파일 오브젝트로 불러오기
        ClassPathResource resource = new ClassPathResource("files/level.json");
        ObjectMapper mapper = new ObjectMapper();
        // 불러온 파일을 List로 변환하기
        List<Level> levelList = mapper.readValue(resource.getFile(), new TypeReference<>() {
        });

        // List를 돌면서 가져온 유저레벨에 맞는 값 넣어주기
        for (Level value : levelList) {
            if (level == value.getLevel()) {
                levelSource = value;
            }
        }


        return levelSource;
    }

    public static int checkUserLevel(User user) throws IOException {
        int userLevel = 1;

        // level.json 파일 오브젝트로 불러오기
        ClassPathResource resource = new ClassPathResource("files/level.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Level> levelList = mapper.readValue(resource.getFile(), new TypeReference<>() {
        });

        if (user.getRoles().equals("ROLE_ADMIN")) {
            userLevel = 100;
        } else if (user.getRoles().equals("ROLE_MANAGER")) {
            userLevel = 99;
        } else {
            // List를 돌면서 가져온 포인트에 맞는 유저레벨 반환하기
            for (Level level : levelList) {
                if (level.getPoint() <= user.getPoint()) {
                    userLevel = level.getLevel();
                }
            }
        }

        return userLevel;
    }
}
