package com.cloudfox.grave.utils;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import java.io.IOException;

public class SendMessageUtil {

    // 短信应用 SDK AppID
    private static int appid = 1400315597; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    private static String appkey = "573a2203f67f2551cc368c452a59a322";
    // 短信模板 ID，需要在短信应用中申请
    private static int damageRemindId = 265391; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
    private static int checkId = 534207; // 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
    // 签名
    private static String smsSign = "佳博软件"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请

    public static void send(String number,String info,String... phones) throws HTTPException, IOException {
        String[] params = {number,info};
        SmsMultiSender msender = new SmsMultiSender(appid, appkey);
        SmsMultiSenderResult result =  msender.sendWithParam("86", phones,
                damageRemindId, params, smsSign, "", "");
        System.out.println(result);
    }

    public static void checkSend(String code,String... phones) throws HTTPException, IOException {
        String[] params = {code};
        SmsMultiSender msender = new SmsMultiSender(appid, appkey);
        SmsMultiSenderResult result =  msender.sendWithParam("86", phones,
                checkId, params, smsSign, "", "");
        System.out.println(result);
    }

}
