package com.duoduo.blog.mapper;

import com.duoduo.blog.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-06-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


//    User selectUserByUsername(String username);

}
