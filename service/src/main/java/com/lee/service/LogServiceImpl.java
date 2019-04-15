package com.lee.service;

import com.lee.dao.mysql.LogPo;
import com.lee.dao.repository.Log.LogRepository;
import com.lee.service.interfaces.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private IdService idService;

    public void saveLog(LogPo po) {
        po.setId(idService.nextId());
        logRepository.save(po);
    }
}
