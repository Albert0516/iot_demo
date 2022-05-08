package Sensor;
import Util.HttpConnect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Wheather {
    public static final int TEMPERATURE = 1;
    public static final int HUMIDITY = 2;
    private static String deviceId = "lumi.158d00066b1d56";
    private static String opt_temperature = "0.1.85";
    private static String opt_humidity = "0.2.85";

    private static String url = "https://developer.aqara.com/open-server/console/debug/query/resource";

    public static String requestData(int requestType)
    {
        return HttpConnect.sendRequest(url,"POST",getRequestStr(requestType),true);
    }

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
