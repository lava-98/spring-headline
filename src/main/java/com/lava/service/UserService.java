package com.lava.service;

import com.lava.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lava.utils.Result;

/**
* @author Lava
* @description 针对表【news_user】的数据库操作Service
* @createDate 2023-10-18 15:38:03
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);
}
