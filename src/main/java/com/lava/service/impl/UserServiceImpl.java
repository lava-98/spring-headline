package com.lava.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lava.pojo.User;
import com.lava.service.UserService;
import com.lava.mapper.UserMapper;
import com.lava.utils.JwtHelper;
import com.lava.utils.MD5Util;
import com.lava.utils.Result;
import com.lava.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
* @author Lava
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2023-10-18 15:38:03
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);
        if (loginUser == null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if (!StringUtils.isEmpty(loginUser.getUserPwd())
                && loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))){
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            System.out.println(loginUser);
            HashMap map = new HashMap<>();
            map.put("token",token);
            return Result.ok(map);
        }
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {
        if (jwtHelper.isExpiration(token)){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        int userId = jwtHelper.getUserId(token).intValue();
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUid,userId);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (user != null){
            user.setUserPwd("");
            HashMap<Object, Object> map = new HashMap<>();
            map.put("loginUser",user);
            return Result.ok(map);
        }
        return Result.build(null,ResultCodeEnum.NOTLOGIN);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,username);
        int i = userMapper.selectCount(lambdaQueryWrapper).intValue();
        if (i == 0 ){
            return Result.ok(null);
        }
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        int i = userMapper.selectCount(lambdaQueryWrapper).intValue();
        if (i == 0 ){
            user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
            userMapper.insert(user);
            return Result.ok(null);
        }
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }
}




