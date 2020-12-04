package com.xiaocai.base.demo.controller;


import com.xiaocai.base.demo.common.base.BaseController;
import com.xiaocai.base.demo.common.base.BaseService;
import com.xiaocai.base.demo.common.message.CommonResult;
import com.xiaocai.base.demo.service.base.UserBsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xiaocai.Zhang
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserBsService userBsService;

    @Override
    public BaseService getBaseService() {
        return userBsService;
    }

    /**
     * http://localhost:8805/user/getById
     */

    /**
     * http://localhost:8805/user/doUser
     * @return
     */
    @RequestMapping("/doUser")
    public CommonResult doUser() {

       return userBsService.querySpecialAgeOver20();
    }
}
