package com.zhao.service;

import com.zhao.pojo.Category;
import com.zhao.vo.CategoryVO;
import com.zhao.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有大分类
     * @return
     */
    List<Category> queryCats();

    List<CategoryVO> querySubCat(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     * @param rootCatId
     * @return
     */
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);

}
