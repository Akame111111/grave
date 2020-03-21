package com.cloudfox.grave.controller.wx;


import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.entity.Admin;
import com.cloudfox.grave.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
@RestController
@RequestMapping("wx/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    @ApiOperation(value = "拉取用户信息",notes = "拉取用户信息")
    public ServerResponse login(String code) {
        return userService.login(code);
    }

    @ApiOperation(value = "认证接口",notes = "认证接口")
    @PostMapping("verify")
    public ServerResponse verify(String openid, String tel, String name) {
        return userService.verify(openid, tel, name);
    }


}

