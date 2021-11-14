package com.devfox.board.service.user;

import com.devfox.board.model.Point;
import com.devfox.board.model.Setting;
import com.devfox.board.model.User;
import com.devfox.board.repository.PointRepository;
import com.devfox.board.repository.SettingRepository;
import com.devfox.board.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PointServiceImpl implements PointService {

    private final UserRepository userRepository;
    private final PointRepository pointRepository;
    private final SettingRepository settingRepository;

    @Override
    public boolean save(Point point) {
        Point result = pointRepository.save(point);
        return result != null;
    }

    @Override
    public String calculatePoint(String username, int point) {

        User user = userRepository.findByUsername(username);

        return user.getNickname() + "님 " + point + "포인트가 적립되었습니다.";
    }

    @Override
    public void contentReadPointInsert(String username) {
        User user = userRepository.findByUsername(username);
        Point point = pointRepository.findByUsername(username);
        List<Point> pointLog;
        Point pointMsg = new Point();
        Setting setting = settingRepository.findById(1);

        // setting의 컨텐츠를 읽었을때 주는 포인트를 불러와 현재 포인트와 더한다.
        int contentReadPoint = setting.getContentReadPoint();
        user.setPoint(user.getPoint() + contentReadPoint);

        // Point의 log를 불러와 json 을 추가해준다.
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode pointMsgString = mapper.createObjectNode();
        pointMsgString.put("msg", "게시물 읽기로 " + contentReadPoint + "p가 적립되었습니다.");
        pointMsgString.put("regdate", String.valueOf(new Date()));

        try {
            if (point.getLog() == null) {
                ArrayNode arrayNode = mapper.createArrayNode();
                arrayNode.addAll(List.of(pointMsgString));
            } else {
                pointLog = mapper.readValue(point.getLog(), new TypeReference<>() {
                });
                pointLog.add(pointMsg);
                point.setLog(pointLog.toString());
            }

            pointRepository.save(point);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        userRepository.save(user);

    }
}
