package com.cloudfox.grave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudfox.grave.common.CommonKeys;
import com.cloudfox.grave.common.OtherApi;
import com.cloudfox.grave.common.ServerResponse;
import com.cloudfox.grave.common.WxMappingJackson2HttpMessageConverter;
import com.cloudfox.grave.entity.User;
import com.cloudfox.grave.mapper.UserMapper;
import com.cloudfox.grave.response.WxResponse;
import com.cloudfox.grave.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skuyu
 * @since 2020-03-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ServerResponse login(String code) {
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        ResponseEntity<WxResponse> response = restTemplate.getForEntity(OtherApi.WX_LOGIN + "?" + "appid=" + CommonKeys.WX_APPID + "&secret=" + CommonKeys.WX_SECRET +"&js_code=" + code + "&grant_type=authorization_code", WxResponse.class);
        WxResponse wxResponse = response.getBody();
        if(wxResponse == null) {
            return ServerResponse.createByErrorMessage("请求openId失败");
        }
        if(wxResponse.getErrcode() != 0) {
            return ServerResponse.createByErrorMessage(wxResponse.getErrmsg());
        }
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("openid", wxResponse.getOpenid()));
        if (user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorCodeMessage(3, wxResponse.getOpenid());
    }

    @Override
    public ServerResponse verify(String openid, String tel, String name) {
        if(baseMapper.selectCount(new QueryWrapper<User>().eq("tel", tel)) > 0) {
            return ServerResponse.createByErrorMessage("该手机号已被绑定");
        }
        if(baseMapper.selectCount(new QueryWrapper<User>().eq("openid", openid)) > 0) {
            return ServerResponse.createByErrorMessage("改微信号已绑定,请重新进入小程序");
        }
        User user = new User();
        user.setOpenid(openid);
        user.setRealname(name);
        user.setTel(tel);
        LocalDateTime time = LocalDateTime.now();
        user.setCreateTime(time);
        user.setUpdateTime(time);

        int count = baseMapper.insert(user);
        if(count == 0) {
            return ServerResponse.createByErrorMessage("创建失败");
        }
        return ServerResponse.createBySuccess(user);
    }


}
