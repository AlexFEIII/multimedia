package com.example.multimedia.service.ServiceImpl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;
import java.util.Random;

public class DuanXinService {
    private final int appid = 1400117958;
    private final String appKey = "5e7510ecfd888798d220162d5b456d68";
    public String duanXin(){
        String smsSign = "alexfei";
        String code = getCode();
        String[] params = new String[]{code,"2"};
        try {
            SmsSingleSender sender = new SmsSingleSender(appid, appKey);
            SmsSingleSenderResult result = sender.sendWithParam("86", "15990313593",
                    166899,params,smsSign, "", "");
            if (!result.errMsg.equals("OK")){
                return "ERROR";
            }
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return code;
    }

    public String getCode(){
        String code = String.valueOf(new Random().nextInt(1000000));
        if (code.length() < 6){
            StringBuffer stringBuffer = new StringBuffer(code);
            for (int i = 0;i < 6-code.length();i ++)
                stringBuffer.insert(0,0);
            code = stringBuffer.toString();
        }
        return code;
    }
}
