package com.cloudfox.grave.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Info对象", description="")
public class Info implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "陵园名称")
    private String name;

    @ApiModelProperty(value = "轮播图路径，逗号隔开")
    private String bannerUrl;

    @ApiModelProperty(value = "介绍")
    private String introduce;

    @ApiModelProperty(value = "电话，逗号隔开")
    private String tel;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "网站")
    private String webUrl;


}
