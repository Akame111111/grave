package com.cloudfox.grave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudfox.grave.common.Const;
import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.dto.TributeDto;
import com.cloudfox.grave.entity.Tribute;
import com.cloudfox.grave.mapper.TributeMapper;
import com.cloudfox.grave.service.TributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
@Service
public class TributeServiceImpl extends ServiceImpl<TributeMapper, Tribute> implements TributeService {

    @Override
    public ServerResponse create(Tribute tribute, String price) {
        if(baseMapper.selectCount(new QueryWrapper<Tribute>().eq("name", tribute.getName())) != 0) {
            return ServerResponse.createByErrorMessage("该名称已存在,创建失败");
        }
        if(tribute.getType().intValue() == Const.TributeTypeEnum.charge.getCode() ) {
            tribute.setPrice(new BigDecimal(price));
        }
        int count = baseMapper.insert(tribute);
        if(count == 0) {
            return ServerResponse.createByErrorMessage("创建失败");
        }
        return ServerResponse.createBySuccess(toDto(tribute));
    }

    @Override
    public ServerResponse alterUse(Integer id) {
        Tribute tribute = baseMapper.selectById(id);
        if(tribute == null) {
            return ServerResponse.createByErrorMessage("未找到该商品");
        }
        if(tribute.getIsUse() == Const.TributeUseEnum.down.getCode()) {
            tribute.setIsUse(Const.TributeUseEnum.up.getCode());
        } else {
            tribute.setIsUse(Const.TributeUseEnum.down.getCode());
        }

        return ServerResponse.createBySuccess(tribute);
    }

    private TributeDto toDto(Tribute tribute) {
        TributeDto dto = new TributeDto();
        BeanUtils.copyProperties(tribute, dto);
        dto.setIsUseName(Const.TributeUseEnum.getDesc(dto.getIsUse()));
        dto.setTypeName(Const.TributeTypeEnum.getDesc(dto.getType()));

        return dto;
    }

    @Override
    public ServerResponse alterInfo(Integer id, String note, Integer duration, String price) {
        Tribute tribute = baseMapper.selectById(id);
        if(tribute == null) {
            return ServerResponse.createByErrorMessage("未找到该用品");
        }
        if(!StringUtils.isEmpty(note)) {
            tribute.setNote(note);
        }
        if(!StringUtils.isEmpty(duration)) {
            tribute.setDuration(duration);
        }
        if(!StringUtils.isEmpty(price)) {
            tribute.setPrice(new BigDecimal(price));
        }
        // todo 如果更新前后对象无变化 count为何值
        int count = baseMapper.updateById(tribute);
        if(count == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccess(toDto(tribute));
    }


}
