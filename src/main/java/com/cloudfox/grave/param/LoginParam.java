package com.cloudfox.grave.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author skuyu
 * @since 2019/8/1 19:12
 */
@Setter
@Getter
@ApiModel(value = "登录参数",description = "用户名和密码")
public class LoginParam {

    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

}
