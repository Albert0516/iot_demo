package Service;

import Util.HttpConnect;
import com.alibaba.fastjson.JSONObject;

import java.util.Scanner;

/**
 * 获取授权码及授权验证
 */
public class AccessAuthorize {

    static final String intentGetToken = "config.auth.getToken";
    static final String intentGetAuthCode = "config.auth.getAuthCode";

    public static String getAccessToken(String timePeriod, Scanner scanner){
        String responseStr = getAccessCode(timePeriod);
        System.out.println("请输入手机获取到的验证码：");
        String code = scanner.nextLine();
        String jsonStr = getCodePlayLoad(code);
        return HttpConnect.sendPostRequest(HttpConnect.consoleDebugApi, jsonStr);
    }

    private static String getAccessCode(String timePeriod){
        String jsonStr = getRequestStr(timePeriod);
        return HttpConnect.sendPostRequest(HttpConnect.consoleDebugApi, jsonStr);
    }

    private static String getCodePlayLoad(String code){
        if(code.isEmpty()) throw new RuntimeException("验证码为空！");
        JSONObject data = new JSONObject();
        data.put("authCode",code);
        data.put("account", Login.aqaraID);
        data.put("accountType",0);

        return HttpConnect.packageRequestData(data, intentGetToken,false);
    }

    private static String getRequestStr(String validTime)
    {
        if (validTime.isEmpty()) throw new RuntimeException("参数为空！");

        JSONObject data = new JSONObject();
        data.put("account", Login.aqaraID);
        data.put("accountType",0);
        data.put("accessTokenValidity",validTime);

        return HttpConnect.packageRequestData(data,intentGetAuthCode,false);
    }
}
