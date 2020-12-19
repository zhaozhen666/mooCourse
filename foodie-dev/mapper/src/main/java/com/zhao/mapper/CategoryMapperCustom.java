package com.zhao.mapper;

import com.zhao.vo.CategoryVO;
import com.zhao.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {

    List<CategoryVO> querySubCat(@Param("rootCatId")Integer rootCatId);

    public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);

}
