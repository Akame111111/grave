package com.cloudfox.grave.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value="Grave对象", description="")
public class Grave implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "区域id")
    private Integer areaId;

    @ApiModelProperty(value = "申请人")
    private String applicant;

    @ApiModelProperty(value = "电话")
    private String tel;

    @ApiModelProperty(value = "住址")
    private String place;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "总价")
    private BigDecimal total;

    @ApiModelProperty(value = "付款金额（定金）")
    private BigDecimal payment;

    @ApiModelProperty(value = "操作人")
    private Integer adminId;

    @ApiModelProperty(value = "操作人姓名")
    private String adminName;

    @ApiModelProperty(value = "位置id")
    private Integer fixId;

    @ApiModelProperty(value = "下葬类型id")
    private Integer typeId;

    @ApiModelProperty(value = "下葬类型")
    private String typeName;

    @ApiModelProperty(value = "面积")
    private BigDecimal space;

    @ApiModelProperty(value = "规格（单穴、双穴。。）")
    private String specs;

    @ApiModelProperty(value = "0未使用，1已使用")
    private Boolean status;

    @ApiModelProperty(value = "照片路径")
    private String photoUrl;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;


}
