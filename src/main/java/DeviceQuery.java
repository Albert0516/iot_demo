import Util.HttpConnect;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DeviceQuery {
    static final String url = "https://developer.aqara.com/open-server/console/api/debug";

    public static String QueryDeviceInfo()
    {
        String jsonStr = getRequestStr();
        return HttpConnect.sendRequest(url,"POST", jsonStr,true);
    }

    private static String getRequestStr()
    {
        JSONArray deviceIds = new JSONArray();
        deviceIds.add("lumi1.54ef443915a0");
        JSONObject data = new JSONObject();
        data.put("dids",deviceIds);
        data.put("positionId","");
        data.put("pageNum", 1);
        data.put("pageSize", 10);

        JSONObject requestParams = new JSONObject();
        requestParams.put("intent","query.device.info");
        requestParams.put("data",data);

        JSONObject param = new JSONObject();
        param.put("appId", "9660557114988584965dfd41");
        param.put("url", "https://aiot-test.aqara.com/v3.0/open/api");
        param.put("requestParams", requestParams);

        param.put("useServer", 1);
        param.put("accessToken", HttpConnect.accessToken);
        return param.toString();
    }
}
