package com.zhao.service.impl;

import com.zhao.service.StuService;
import com.zhao.service.TestTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestTransServiceImpl implements TestTransService {

    @Autowired
    StuService stuService;
    @Override
    //@Transactional(propagation = Propagation.REQUIRED)
    public void saveStudent() {
      stuService.saveStuParent();

      stuService.saveStuChild();
    }
}
