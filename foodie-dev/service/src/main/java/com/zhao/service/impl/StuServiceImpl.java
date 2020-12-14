package com.zhao.service.impl;

import com.zhao.mapper.StuMapper;
import com.zhao.pojo.Stu;
import com.zhao.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    StuMapper stuMapper;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveStuChild() {
        saveChildren1();
        int i =1/0;
        saveChildren2();
    }

    public void  saveChildren1(){
        Stu stu = new Stu();
        stu.setAge(11);
        stu.setName("test1");
        stuMapper.insert(stu);
    }

    public void  saveChildren2(){
        Stu stu = new Stu();
        stu.setAge(22);
        stu.setName("test1");
        stuMapper.insert(stu);
    }

    @Override
    //@Transactional(propagation = Propagation.REQUIRED)
    public void saveStuParent() {
        Stu stu = new Stu();
        stu.setAge(30);
        stu.setName("parent");
        stuMapper.insert(stu);
    }
}
