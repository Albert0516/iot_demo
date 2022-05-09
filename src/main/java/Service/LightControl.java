package Service;

import Util.HttpConnect;
import com.alibaba.fastjson.JSONObject;

public class LightControl {
    private static String url = "https://developer.aqara.com/open-server/console/debug/write/resource";
    private static String deviceId = "lumi.54ef44100035ddd9";
    private static boolean lightState = false;


    public static String OperateLight()
    {
        String result = HttpConnect.sendRequest(url,"POST", getRequestStr(), true);
        return result.isEmpty()? "fail" : result;
    }

    private static String getRequestStr()
    {
        JSONObject data = new JSONObject();
        lightState = !lightState;
        String code = lightState?"1":"0";
        data.put("4.1.85",code);

        JSONObject paramStr = new JSONObject();
        paramStr.put("did",deviceId);
        paramStr.put("data",data);
        paramStr.put("appId", HttpConnect.appId);
        paramStr.put("openId", HttpConnect.openId);
        paramStr.put("useServer",1);

        return paramStr.toString();
    }
}
