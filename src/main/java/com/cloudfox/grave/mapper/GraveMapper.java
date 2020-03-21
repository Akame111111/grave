package com.cloudfox.grave.mapper;

import com.cloudfox.grave.entity.Grave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
public interface GraveMapper extends BaseMapper<Grave> {

    @Select("select photo_url from grave where id = #{id}")
    String selectPicture(Integer id);
}
