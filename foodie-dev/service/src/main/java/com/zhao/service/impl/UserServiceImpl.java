package com.zhao.service.impl;

import com.zhao.bo.UserBo;
import com.zhao.common.enums.Sex;
import com.zhao.common.util.DateUtil;
import com.zhao.common.util.MD5Utils;
import com.zhao.mapper.UsersMapper;
import com.zhao.pojo.Users;
import com.zhao.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UsersMapper usersMapper;
    @Autowired
    private Sid sid;

    private static final String USER_FACE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608145838506&di=91ec84f0d7edbe39c98b6f559cbbd885&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F0cd238587a0984d24b8688ad35c187da3ace5314317c-KPcKiS_fw658";
    @Override
    public boolean checkIsExistUser(String username) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);

        Users result = usersMapper.selectOneByExample(userExample);

        return result == null ? false : true;
    }

    @Override
    public Users insertUser(UserBo userBO) {
        String userId = sid.nextShort();

        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 默认用户昵称同用户名
        user.setNickname(userBO.getUsername());
        // 默认头像
        user.setFace(USER_FACE);
        // 默认生日
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        // 默认性别为 保密
        user.setSex(Sex.secret.type);

        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);

        return user;
    }

    @Override
    public Users checkExsit(UserBo userBo) throws Exception {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", userBo.getUsername());
        userCriteria.andEqualTo("password", MD5Utils.getMD5Str(userBo.getPassword()));
        Users result = usersMapper.selectOneByExample(userExample);


        return result;
    }
}
