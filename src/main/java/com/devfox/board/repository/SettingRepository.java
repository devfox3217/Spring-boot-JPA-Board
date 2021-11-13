package com.devfox.board.repository;

import com.devfox.board.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Long> {
    Setting findById(long id);
}
