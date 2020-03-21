package com.cloudfox.grave.service;

import com.cloudfox.grave.param.LoginParam;
import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
public interface AdminService extends IService<Admin> {

    ServerResponse create(Admin admin);

    ServerResponse login(LoginParam param);

    ServerResponse delete(int id, Admin admin);

    ServerResponse leave(int id, Admin admin);

    ServerResponse alterPasssword(int id, String newPassword);

    ServerResponse resetPassword(int id, Integer roleId);

    ServerResponse alterNickName(Admin admin, String nickName);

    ServerResponse alterRole(int id, int roleId, Integer adminRoleId);

    ServerResponse listData(List<Admin> leave_time);
}
