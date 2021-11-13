package com.devfox.board.service.user;

import com.devfox.board.model.Point;
import com.devfox.board.model.User;
import com.devfox.board.repository.PointRepository;
import com.devfox.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PointServiceImpl implements PointService {

    private final UserRepository userRepository;
    private final PointRepository pointRepository;

    @Override
    public boolean save(Point point) {
        Point result = pointRepository.save(point);
        return result != null;
    }

    @Override
    public String calculatePoint(String username, int point) {

        User user = userRepository.findByUsername(username);


        String msg = user.getNickname() + "님 " + point + "포인트가 적립되었습니다.";
        return msg;
    }
}
