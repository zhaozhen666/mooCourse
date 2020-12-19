package com.zhao.service.impl;

import com.zhao.common.util.MD5Utils;
import com.zhao.mapper.CarouselMapper;
import com.zhao.pojo.Carousel;
import com.zhao.pojo.Users;
import com.zhao.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    CarouselMapper carouselMapper;
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example carouselExample = new Example(Carousel.class);
        Example.Criteria carouselCriteria = carouselExample.createCriteria();

        carouselCriteria.andEqualTo("isShow", isShow);

       List<Carousel> list = carouselMapper.selectByExample(carouselExample);
       return list;
    }
}
