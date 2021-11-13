package com.devfox.board.util;

import com.devfox.board.model.Level;
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
        List<Level> levelList = mapper.readValue(resource.getFile(), new TypeReference<List<Level>>() {});

        for (Level value : levelList) {
            if (level == value.getLevel()) {
                levelSource = value;
            }
        }


        return levelSource;
    }
}
