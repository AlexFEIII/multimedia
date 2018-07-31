package com.example.multimedia.service.ServiceImpl;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.baidu.aip.speech.AipSpeech;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BaiduService {
    //设置appid/ApiKey/SecretKey
    public static final String APP_ID="11551759";
    public static final String API_KEY="IzQWq0pgOb63Kv2Y0ohurzAA";
    public static final String SECRET_KEY="oXrIUnvdNOs0iVIocQidAt9oW3wkExj6";

    /*
    * 语音转化字符串
    * */
    public String getYuYin(String path){
        //初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID,API_KEY,SECRET_KEY);
        //(可选)设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        JSONObject aseRes = client.asr(path,"wav",16000,null);
        System.out.println(aseRes);
        return aseRes.toString();
    }

    public String getImage(MultipartFile file){
        /*
         * 文本识别：spam = 1 则违规
         * 图片识别：正常 ✔
         * */
        AipContentCensor aipContentCensor = new AipContentCensor(APP_ID,API_KEY,SECRET_KEY);

        try{
            byte[] wenjian = file.getBytes();
            String str = aipContentCensor.imageCensorUserDefined(wenjian, null).get("conclusion").toString();
            if (str.equals("不合规") || str.contains("色情") || str.equals("SM") || str.equals("性玩具"))
                return "N";
            return "Y";
        }catch (Exception e){
            //ignore
        }

        return null;
    }

}
