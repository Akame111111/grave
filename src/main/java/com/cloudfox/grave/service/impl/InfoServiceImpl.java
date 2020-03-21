package com.cloudfox.grave.service.impl;

import com.cloudfox.grave.entity.Info;
import com.cloudfox.grave.mapper.InfoMapper;
import com.cloudfox.grave.service.InfoService;
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
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {

}
