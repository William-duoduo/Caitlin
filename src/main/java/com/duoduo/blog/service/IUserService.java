package com.duoduo.blog.service;

import com.duoduo.blog.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duoduo.blog.domain.dto.UserLoginDTO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author author
 * @since 2024-06-19
 */
public interface IUserService extends IService<User> {

    String login(UserLoginDTO user);
}
