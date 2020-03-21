package com.cloudfox.grave.service;

import com.cloudfox.grave.common.ServerResponse;

public interface BaseService {

    ServerResponse sendSms(String tel);
}
