package com.duoduo.blog;

import com.duoduo.blog.domain.User;
import com.duoduo.blog.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        User user = new User();
        user.setLoginName("admin");
        user.setLoginPwd(passwordEncoder.encode("wb10g"));
        userMapper.insert(user);
    }


}
