package com.cloudfox.grave.controller.back;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.entity.Tribute;
import com.cloudfox.grave.service.TributeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
@RestController
@RequestMapping("back/tribute")
public class TributeController {

    @Autowired
    private TributeService tributeService;

    @ApiOperation(value = "list上架物品", notes = "list上架物品")
    @GetMapping("list")
    public ServerResponse list() {
        return ServerResponse.createBySuccess(tributeService.list(new QueryWrapper<Tribute>().eq("is_use", 1)) );
    }

    @ApiOperation(value = "list所有物品", notes = "list所有物品")
    @GetMapping("list_all")
    public ServerResponse listAll() {
        return ServerResponse.createBySuccess(tributeService.list(new QueryWrapper<Tribute>().orderByDesc("is_use")) );
    }

    @ApiOperation(value = "创建物品", notes = "创建物品")
    @PostMapping("create")
    public ServerResponse create(Tribute tribute, String price) {
        return tributeService.create(tribute, price);
    }

    @ApiOperation(value = "更改上下架状态", notes = "更改上下架状态")
    @PutMapping("alter_use")
    public ServerResponse alterUse(Integer id) {
        return tributeService.alterUse(id);
    }

    /**
     *
     * @param id
     * @param note 介绍
     * @param duration 时长
     * @param price 价格
     * @return
     */
    @ApiOperation(value = "介绍", notes = "更改上下架状态")
    @PutMapping("alter_info")
    public ServerResponse alterInfo(Integer id, String note, Integer duration, String price) {
        return tributeService.alterInfo(id, note, duration, price);
    }
}

