package com.xiaocai.base.demo.mapper;


import com.xiaocai.base.demo.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Xiaocai.Zhang
 */


public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {


    List<User> selectSpecialAgeOver20(User user);
}
