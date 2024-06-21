package com.duoduo.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.duoduo.blog.domain.User;
import com.duoduo.blog.domain.dto.UserLoginDTO;
import com.duoduo.blog.mapper.UserMapper;
import com.duoduo.blog.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duoduo.common.security.domain.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-06-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(UserLoginDTO user) {
        System.out.println("user = " + user);
        String token = "";
        String loginName = user.getLoginName();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getLoginName, loginName);
        User one = getOne(queryWrapper);
        System.out.println("one = " + one);
        if (one != null && one.getEnable() == 1) {
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(user.getLoginName(), user.getLoginPwd());
            Authentication authenticate = authenticationManager.authenticate(upat);
            MyUserDetails myUserDetails = (MyUserDetails) authenticate.getPrincipal();
            System.out.println("myUserDetails = " + myUserDetails);
            token = UUID.randomUUID().toString();

        }
        return token;
    }
}
