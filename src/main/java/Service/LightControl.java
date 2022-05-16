package Service;

import Util.HttpConnect;
import com.alibaba.fastjson.JSONObject;

/**
 * 灯光控制逻辑
 */
public class LightControl {
    //private static String deviceId = "lumi.54ef44100035ddd9";
    private static String deviceId = "lumi.54ef44100035c7d1";
    private static boolean lightState = false;


    /**
     * 操作灯泡-开/关灯
     * @return
     */
    public static String OperateLight()
    {
        String result = HttpConnect.sendPostRequest(HttpConnect.resourceWriteApi, getRequestStr());
        return result.isEmpty()? "fail" : result;
    }

    /**
     * 根据传入的请求对象，封装aqaraApi请求数据 - 操作灯泡-开/关灯
     * @return
     */
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
