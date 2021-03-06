package com.cloudfox.grave.service;

import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
public interface UserService extends IService<User> {

    ServerResponse login(String code);

    ServerResponse verify(String openid, String tel, String name);
}
