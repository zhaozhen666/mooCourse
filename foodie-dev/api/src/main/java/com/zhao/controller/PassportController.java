package com.zhao.controller;

import com.zhao.bo.UserBo;
import com.zhao.common.util.CookieUtils;
import com.zhao.common.util.IMOOCJSONResult;
import com.zhao.common.util.JsonUtils;
import com.zhao.pojo.Users;
import com.zhao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@RestController
@Api(value = "用户注册",tags = {"用户注册相关接口"})
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @GetMapping("userIsExist")
    @ApiOperation(value = "用户注册",httpMethod = "GET",notes = "用户是否存在")
    public IMOOCJSONResult userIsExist(@RequestParam String username){
        if (StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        boolean isExist = userService.checkIsExistUser(username);
        if (isExist){
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }

        return IMOOCJSONResult.ok();
    }


    @PostMapping("register")
    @ApiOperation(value = "用户注册",httpMethod = "POST",notes = "用户注册")
    public IMOOCJSONResult userIsExist(@RequestBody UserBo userBo,HttpServletRequest req,HttpServletResponse resp){
        String password =userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();
        String username = userBo.getUsername();
        if (StringUtils.isBlank(password)||StringUtils.isBlank(confirmPassword)
                ||StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }
        if (!password.equals(confirmPassword)){
          return IMOOCJSONResult.errorMsg("两次密码输入不一致");

        }
        if (password.length()<6){
            return IMOOCJSONResult.errorMsg("密码长度不得小于6位");
        }
        boolean isExist = userService.checkIsExistUser(username);
        if (isExist){
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        userService.insertUser(userBo);
        CookieUtils.setCookie(req,resp,"user", JsonUtils.objectToJson(userBo));
        return IMOOCJSONResult.ok();
    }

    @PostMapping("login")
    @ApiOperation(value = "用户登录",httpMethod = "POST",notes = "用户注册")
    public IMOOCJSONResult userLogin(@RequestBody UserBo userBo, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String password =userBo.getPassword();
        String username = userBo.getUsername();
        if (StringUtils.isBlank(password)
                ||StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }
       Users users = userService.checkExsit(userBo);
        if (users==null){
            return IMOOCJSONResult.errorMsg("用户名不存在");
        }
        Users users1 = setNullProperty(users);
        CookieUtils.setCookie(req,resp,"user", URLEncoder.encode(JsonUtils.objectToJson(users1)));
        return IMOOCJSONResult.ok(users);
    }
    @PostMapping("logout")
    @ApiOperation(value = "用户退出",httpMethod = "POST",notes = "用户退出")
    public IMOOCJSONResult userLogin(@RequestParam String userId, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CookieUtils.deleteCookie(req,resp,"user");
        return IMOOCJSONResult.ok();
    }
    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }
}
