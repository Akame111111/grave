package com.cloudfox.grave.controller.back;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudfox.grave.param.LoginParam;
import com.cloudfox.grave.common.Const;
import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.entity.Admin;
import com.cloudfox.grave.service.AdminService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
@RestController
@RequestMapping("back/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("create")
    @ApiOperation(value = "创建管理员",notes = "创建管理员接口")
    public ServerResponse create(Admin admin) {
        return adminService.create(admin);
    }

    @ApiOperation(value = "登录",notes = "管理员登录接口")
    @PostMapping("login")
    public ServerResponse login(@ApiParam(name = "传入的对象, username, password",required = true) LoginParam param, @ApiIgnore HttpSession session){
        ServerResponse response = adminService.login(param);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @ApiOperation(value = "删除",notes = "管理员删除接口")
    @DeleteMapping("delete")
    public ServerResponse delete(@ApiParam(name = "要删除的管理员id",required = true) int id, @ApiIgnore HttpSession session) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_USER);
        return adminService.delete(id, admin);
    }

    @ApiOperation(value = "离职",notes = "管理员离职接口")
    @PutMapping("leave")
    public ServerResponse leave(@ApiParam(name = "离职的管理员id",required = true) int id,@ApiIgnore HttpSession session) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_USER);
        return adminService.leave(id, admin);
    }

    @ApiOperation(value = "更新密码", notes = "更新管理员密码接口")
    @PutMapping("alter_password")
    public ServerResponse alterPassword(@ApiParam(name = "id",required = true) int id,@ApiParam(name = "newPassword", required = true) String newPassword) {
        return adminService.alterPasssword(id, newPassword);
    }

    @ApiOperation(value = "重置密码", notes = "管理员重置密码接口")
    @PutMapping("reset_password")
    public ServerResponse resetPassword(@ApiParam(name = "id",required = true) int id,@ApiIgnore HttpSession session) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_USER);
        return adminService.resetPassword(id, admin.getRoleId());
    }

    @ApiOperation(value = "更新信息", notes = "管理员更新自己昵称接口")
    @PutMapping("alter")
    public ServerResponse alter(@ApiParam(name = "nickName",required = true) String nickName,@ApiIgnore HttpSession session) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_USER);
        return adminService.alterNickName(admin, nickName);
    }

    @ApiOperation(value = "修改管理员角色", notes = "修改管理员角色接口,总管理员可修改")
    @PutMapping("alter_role")
    public ServerResponse alterRole(@ApiParam(name = "id",required = true) int id,@ApiParam(name = "roleId",required = true) int roleId,@ApiIgnore HttpSession session) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_USER);
        return adminService.alterRole(id, roleId, admin.getRoleId());
    }

    @ApiOperation(value = "list", notes = "list管理员")
    @GetMapping("list")
    public ServerResponse list() {
        return adminService.listData(adminService.list(new QueryWrapper<Admin>().orderByAsc("leave_time")));
    }


}

