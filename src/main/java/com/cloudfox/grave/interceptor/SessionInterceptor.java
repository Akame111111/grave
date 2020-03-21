package com.cloudfox.grave.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.cloudfox.grave.common.Const;
import com.cloudfox.grave.common.ResponseCode;
import com.cloudfox.grave.common.ServerResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author skuyu
 * @since 2019/8/1 19:09
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //该方法在业务处理之前被调用，先用来处理登录验证
        if (request.getRequestURI().endsWith("login")){
            //登录接口，直接放行
            return true;
        }
        Object obj = request.getSession().getAttribute(Const.CURRENT_USER);
        if (obj == null){
            sendJsonMessage(response, ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录"));
            return false;
        }
        return true;
    }

    //返回数据
    private static void sendJsonMessage(HttpServletResponse response, Object obj) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(JSONObject.toJSONString(obj, SerializerFeature.IgnoreNonFieldGetter));
        writer.close();
        response.flushBuffer();
    }

}
