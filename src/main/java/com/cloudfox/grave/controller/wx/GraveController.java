package com.cloudfox.grave.controller.wx;


import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.service.GraveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
@RestController
@RequestMapping("grave")
public class GraveController {

    @Autowired
    private GraveService graveService;

    @ApiOperation(value = "获取墓穴照片图片接口",notes = "获取墓穴照片图片接口")
    @GetMapping("picture")
    public ServerResponse picture(Integer id) {
        return graveService.picture(id);
    }



}

