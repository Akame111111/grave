package com.cloudfox.grave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudfox.grave.param.LoginParam;
import com.cloudfox.grave.common.Const;
import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.entity.Admin;
import com.cloudfox.grave.entity.Role;
import com.cloudfox.grave.mapper.AdminMapper;
import com.cloudfox.grave.mapper.RoleMapper;
import com.cloudfox.grave.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudfox.grave.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public ServerResponse create(Admin admin) {
        // tel,username查重
        if(baseMapper.selectCount(new QueryWrapper<Admin>().eq("username", admin.getUsername())) != 0) {
            return ServerResponse.createByErrorMessage("username已存在,创建失败");
        }
        if(baseMapper.selectCount(new QueryWrapper<Admin>().eq("tel", admin.getTel())) != 0) {
            return ServerResponse.createByErrorMessage("手机号已存在,创建失败");
        }
        admin.setPassword(MD5Util.MD5EncodeUtf8(admin.getPassword()));
        Role role = roleMapper.selectById(admin.getRoleId());
        if(role == null) {
            return ServerResponse.createByErrorMessage("未找到该权限");
        }
        admin.setRoleName(role.getName());
        admin.setCreateTime(LocalDateTime.now());
        if(baseMapper.insert(admin) == 0) {
            return ServerResponse.createByErrorMessage("创建失败");
        }
        admin.setPassword("");
        return ServerResponse.createBySuccess(admin);
    }

    @Override
    public ServerResponse login(LoginParam param) {
        Admin admin = baseMapper.selectOne(new QueryWrapper<Admin>().eq("username", param.getUsername()).eq("password", MD5Util.MD5EncodeUtf8(param.getPassword())));
        if (admin == null) {
            return ServerResponse.createByErrorMessage("账号密码错误");
        }
        if(!StringUtils.isEmpty(admin.getLeaveTime())) {
            return ServerResponse.createByErrorMessage("该用户已离职,登录失败");
        }
        admin.setLastLoginTime(LocalDateTime.now());

        baseMapper.updateById(admin);
        admin.setPassword("");
        return ServerResponse.createBySuccess(admin);
    }

    @Override
    public ServerResponse delete(int id, Admin ad) {
        if(ad.getId().intValue() == id) {
            return ServerResponse.createByErrorMessage("无法操作自己");
        }
        if(ad.getRoleId().intValue() != Const.AdminList.ADMINHEAD.getCode()) {
            return ServerResponse.createByErrorMessage("权限不足,操作失败");
        }
        Admin admin = baseMapper.selectById(id);
        if(admin == null) {
            return ServerResponse.createByErrorMessage("要删除的目标不存在");
        }
        int count = baseMapper.deleteById(admin);
        if(count == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse leave(int id, Admin ad) {
        if(ad.getId().intValue() == id) {
            return ServerResponse.createByErrorMessage("无法操作自己");
        }
        if(ad.getRoleId().intValue() != Const.AdminList.ADMINHEAD.getCode()) {
            return ServerResponse.createByErrorMessage("权限不足,操作失败");
        }
        Admin admin = baseMapper.selectById(id);
        if(admin == null) {
            return ServerResponse.createByErrorMessage("要离职的目标不存在");
        }
        admin.setLeaveTime(LocalDateTime.now());
        int count = baseMapper.updateById(admin);
        if(count == 0) {
            return ServerResponse.createByErrorMessage("操作失败");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse alterPasssword(int id, String newPassword) {
        Admin admin = baseMapper.selectById(id);
        if(admin == null) {
            return ServerResponse.createByErrorMessage("未找到该管理员");
        }
        admin.setPassword(MD5Util.MD5EncodeUtf8(newPassword));

        int count = baseMapper.updateById(admin);
        if(count == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse resetPassword(int id, Integer roleId) {
        if(roleId != Const.AdminList.ADMINHEAD.getCode()) {
            return ServerResponse.createByErrorMessage("越权操作");
        }
        Admin admin = baseMapper.selectById(id);
        if(admin == null) {
            return ServerResponse.createByErrorMessage("未找到该对象");
        }
        admin.setPassword(MD5Util.MD5EncodeUtf8(Const.RESET_PASSWORD));

        int count = baseMapper.updateById(admin);
        if(count == 0) {
            return ServerResponse.createByErrorMessage("重置密码失败");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse alterNickName(Admin admin, String nickName) {
        // 因为拦截器 不登录无法使用 所以默认admin有值
        admin.setNickname(nickName);

        int count = baseMapper.updateById(admin);
        if(count == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse alterRole(int id, int roleId, Integer adminRoleId) {
        if(adminRoleId != Const.AdminList.ADMINHEAD.getCode()) {
            return ServerResponse.createByErrorMessage("越权操作");
        }
        Admin admin = baseMapper.selectById(id);
        if(admin == null) {
            return ServerResponse.createByErrorMessage("未找到该对象");
        }
        Role role = roleMapper.selectById(roleId);
        if(role == null) {
            return ServerResponse.createByErrorMessage("未找到该角色");
        }
        admin.setRoleId(roleId);
        admin.setRoleName(role.getName());
        int count = baseMapper.updateById(admin);
        if(count == 0) {
            return ServerResponse.createByErrorMessage("修改失败");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse listData(List<Admin> list) {
        list.stream().forEach(admin -> admin.setPassword(""));
        return ServerResponse.createBySuccess(list);
    }
}
