package Sensor;
import Util.HttpConnect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 温湿度传感器
 */
public class Weather {
    public static final int TEMPERATURE = 1;
    public static final int HUMIDITY = 2;
    //private static String deviceId = "lumi.158d0007f2b6c5";
    /**
     * 温度传感器id
     */
    private static String deviceId = "lumi.158d0007f2b715";

    /**
     * 传感器请求类别
     * 0.1.85-温度； 0.2.85-湿度
     */
    private static final String opt_temperature = "0.1.85";
    private static final String opt_humidity = "0.2.85";

    /**
     * 请求传感器数据（温度 or 湿度）
     * @param requestType
     * @return
     */
    public static String requestData(int requestType)
    {
        return HttpConnect.sendPostRequest(HttpConnect.resourceQueryApi, getRequestStr(requestType));
    }

    /**
     * 根据传入的请求对象，封装aqaraApi请求数据 - 请求传感器数据（温度 or 湿度）
     * @param requestType
     * @return
     */
    private static String getRequestStr(int requestType)
    {
        JSONArray options = new JSONArray();
        options.add(requestType==1 ? opt_temperature : opt_humidity);

        JSONObject groupData = new JSONObject();
        groupData.put("subjectId",deviceId);
        groupData.put("options",options);

        JSONArray data = new JSONArray();
        data.add(groupData);


        JSONObject param = new JSONObject();
        param.put("data",data);
        param.put("appId", HttpConnect.appId);
        param.put("openId", HttpConnect.openId);
        param.put("useServer", 1);
        param.put("did", deviceId);
        return param.toString();
    }
}
