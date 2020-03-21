package com.cloudfox.grave.service.impl;

import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.entity.Grave;
import com.cloudfox.grave.mapper.GraveMapper;
import com.cloudfox.grave.service.GraveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
@Service
public class GraveServiceImpl extends ServiceImpl<GraveMapper, Grave> implements GraveService {

    @Override
    public ServerResponse picture(Integer id) {
        String url = baseMapper.selectPicture(id);
        return ServerResponse.createBySuccess(url);
    }
}
