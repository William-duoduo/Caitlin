package com.duoduo.blog.controller;


import com.duoduo.blog.domain.dto.UserLoginDTO;
import com.duoduo.blog.service.IUserService;
import com.duoduo.common.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-06-19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody UserLoginDTO loginDTO) {
        String token = userService.login(loginDTO);
        System.out.println("token = " + token);
        return AjaxResult.success();
    }

}
