package com.xiaocai.base.demo.service.base;


import com.xiaocai.base.demo.common.base.BaseService;
import com.xiaocai.base.demo.common.message.CommonResult;
import com.xiaocai.base.demo.mapper.UserMapper;
import com.xiaocai.base.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xiaocai.Zhang
 */
@Service
public class UserBsService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

    public CommonResult querySpecialAgeOver20(){
        User user = new User();
        user.setAge(20);
        List<User> list = userMapper.selectSpecialAgeOver20(user);
        return CommonResult.ok("自定义查询测试", list);
    }
}
