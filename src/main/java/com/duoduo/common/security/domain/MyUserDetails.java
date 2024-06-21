package com.duoduo.common.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import com.duoduo.blog.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

    private Integer id;

    private String loginName;

    private String loginPwd;

    public MyUserDetails(User user) {
        this.id = user.getId();
        this.loginName = user.getLoginName();
        this.loginPwd = user.getLoginPwd();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.loginPwd;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

}
