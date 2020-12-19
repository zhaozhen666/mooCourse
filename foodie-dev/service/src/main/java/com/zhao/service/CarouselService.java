package com.zhao.service;

import com.zhao.pojo.Carousel;

import java.util.List;

public interface CarouselService {
    List<Carousel> queryAll(Integer isShow);
}
