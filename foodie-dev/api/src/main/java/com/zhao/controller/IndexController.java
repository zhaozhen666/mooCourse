package com.zhao.controller;

import com.zhao.common.enums.YesOrNo;
import com.zhao.common.util.IMOOCJSONResult;
import com.zhao.pojo.Carousel;
import com.zhao.pojo.Category;
import com.zhao.service.CarouselService;
import com.zhao.service.CategoryService;
import com.zhao.vo.CategoryVO;
import com.zhao.vo.NewItemsVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    CarouselService carouselService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("carousel")
    @ApiOperation(value = "首页轮播图",httpMethod = "GET",notes = "首页轮播图")
    public IMOOCJSONResult carousel(){
        List<Carousel> list= carouselService.queryAll(YesOrNo.YES.type);
        return IMOOCJSONResult.ok(list);
    }

    @GetMapping("cats")
    @ApiOperation(value = "首页大类",httpMethod = "GET",notes = "首页大类")
    public IMOOCJSONResult cats(){
        List<Category> list= categoryService.queryCats();
        return IMOOCJSONResult.ok(list);
    }

    @GetMapping("subCat/{rootCatId}")
    @ApiOperation(value = "首页小类",httpMethod = "GET",notes = "首页小类")
    public IMOOCJSONResult subCat(
            @ApiParam(name = "rootCatId",value = "一级分类id",required = true)
            @PathVariable Integer rootCatId){
        List<CategoryVO> list= categoryService.querySubCat(rootCatId);
        return IMOOCJSONResult.ok(list);
    }

    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据", notes = "查询每个一级分类下的最新6条商品数据", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public IMOOCJSONResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }

        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return IMOOCJSONResult.ok(list);
    }
}
