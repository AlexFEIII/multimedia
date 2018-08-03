package com.example.multimedia.service.ServiceImpl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DuanXinService {
    private final int appid = 1400117958;
    private final String appKey = "5e7510ecfd888798d220162d5b456d68";
    public String duanXin(){
        String smsSign = "alexfei";
        String[] params = new String[]{getCode(),"2"};
        try {
            SmsSingleSender sender = new SmsSingleSender(appid, appKey);
            SmsSingleSenderResult result = sender.sendWithParam("86", "15990313593",
                    166899,params,smsSign, "", "");
            System.out.print(result);
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
        return "Y";
    }

    public String getCode(){
        String code = String.valueOf(new Random(1000000));
        if (code.length() < 6){
            StringBuffer stringBuffer = new StringBuffer(code);
            for (int i = 0;i < 6-code.length();i ++)
                stringBuffer.insert(0,0);
        }
        return code;
    }
}
