package com.cloudfox.grave.service.impl;

import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.service.BaseService;
import com.cloudfox.grave.utils.SendMessageUtil;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

@Service
public class BaseServiceImpl implements BaseService {

    @Override
    public ServerResponse sendSms(String tel) {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        try {
            SendMessageUtil.checkSend(code + "",tel);
        } catch (HTTPException e) {
            return ServerResponse.createByErrorMessage("与短信服务商连接失败");
        } catch (IOException e) {
            return ServerResponse.createByErrorMessage("发送短信失败");
        }
        return ServerResponse.createBySuccess(code);
    }
}
