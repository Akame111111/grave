package com.cloudfox.grave.service.impl;

import com.cloudfox.grave.entity.Type;
import com.cloudfox.grave.mapper.TypeMapper;
import com.cloudfox.grave.service.TypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

}
