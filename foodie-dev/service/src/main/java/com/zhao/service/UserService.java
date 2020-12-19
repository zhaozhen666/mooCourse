package com.zhao.service;

import com.zhao.bo.UserBo;
import com.zhao.pojo.Users;

public interface UserService {
    boolean checkIsExistUser(String username);

    Users insertUser(UserBo userBo);

    Users checkExsit(UserBo userBo) throws Exception;
}
