package com.zhao.service.impl;

import com.zhao.mapper.CategoryMapper;
import com.zhao.mapper.CategoryMapperCustom;
import com.zhao.pojo.Carousel;
import com.zhao.pojo.Category;
import com.zhao.service.CategoryService;
import com.zhao.vo.CategoryVO;
import com.zhao.vo.NewItemsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryMapperCustom categoryMapperCustom;
    @Override
    public List<Category> queryCats() {
        Example categorylExample = new Example(Category.class);
        Example.Criteria carouselCriteria = categorylExample.createCriteria();

        carouselCriteria.andEqualTo("fatherId", 0);

        List<Category> list = categoryMapper.selectByExample(categorylExample);
        return list;
    }

    @Override
    public List<CategoryVO> querySubCat(Integer rootCatId) {
       return categoryMapperCustom.querySubCat(rootCatId);
    }

    @Override
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {

        Map<String, Object> map = new HashMap<>();
        map.put("rootCatId", rootCatId);

        return categoryMapperCustom.getSixNewItemsLazy(map);
    }
}
