package com.cloudfox.grave.service;

import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.entity.Tribute;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
public interface TributeService extends IService<Tribute> {

    ServerResponse create(Tribute tribute, String price);

    ServerResponse alterUse(Integer id);

    ServerResponse alterInfo(Integer id, String note, Integer duration, String price);
}
