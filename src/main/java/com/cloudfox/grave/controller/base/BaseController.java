package com.cloudfox.grave.controller.base;

import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.service.BaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("base")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @ApiOperation(value = "发送验证码接口",notes = "发送验证码接口")
    @GetMapping("send_sms")
    public ServerResponse sendSms(String tel) {
        return baseService.sendSms(tel);
    }
}
