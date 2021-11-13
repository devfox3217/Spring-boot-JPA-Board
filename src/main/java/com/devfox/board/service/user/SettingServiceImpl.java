package com.devfox.board.service.user;


import com.devfox.board.model.Setting;
import com.devfox.board.repository.SettingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SettingServiceImpl implements SettingService {

    private final SettingRepository settingRepository;

    @Override
    public Setting loadSetting() {
        return settingRepository.findById(1);
    }
}
