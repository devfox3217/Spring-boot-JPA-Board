package com.devfox.board.service.user;

import com.devfox.board.model.Point;

public interface PointService {
    boolean save(Point point);
    String calculatePoint(String username, int point);
    void contentReadPointInsert(String username);
}
