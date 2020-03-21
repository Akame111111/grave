package com.cloudfox.grave.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author skuyu
 * @date 2018/12/23 16:47
 */
@Getter
@Setter
public class WxResponse {

    private String openid;

    private String session_key;

    private String unionid;

    private int errcode;

    private String errmsg;

}
