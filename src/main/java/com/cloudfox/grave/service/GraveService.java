package com.cloudfox.grave.service;

import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.entity.Grave;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
public interface GraveService extends IService<Grave> {

    ServerResponse picture(Integer id);
}
