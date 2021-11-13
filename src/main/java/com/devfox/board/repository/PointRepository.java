package com.devfox.board.repository;

import com.devfox.board.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, String> {
    Point findByUsername(String username);
}
