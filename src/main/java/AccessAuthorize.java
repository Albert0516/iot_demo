import Util.HttpConnect;
import com.alibaba.fastjson.JSONObject;

public class AccessAuthorize {
    static final String url = DeviceQuery.url;
    static final String apiUrl = "https://aiot-test.aqara.com/v3.0/open/api";

    public String getAccessToken(){

        getAccessCode();
        //TODO-获取键盘输入的手机验证码
        String code = "";
        String jsonStr = getCodePlayLoad(code);
        return HttpConnect.sendRequest(url,"POST", jsonStr,true);
    }

    private String getAccessCode(){
        String jsonStr = getRequestStr("7d");
        return HttpConnect.sendRequest(url,"POST", jsonStr,true);
    }

    private String getCodePlayLoad(String code){

        JSONObject data = new JSONObject();
        data.put("authCode",code);
        data.put("account",Login.aqaraID);
        data.put("accountType",0);

        JSONObject reqParam = new JSONObject();
        reqParam.put("intent","config.auth.getToken");
        return getString(data, reqParam);
    }

    private String getRequestStr(String validTime)
    {
        if (validTime.isBlank()) return "";

        JSONObject data = new JSONObject();
        data.put("account",Login.aqaraID);
        data.put("accountType",0);
        data.put("accessTokenValidity",validTime);

        JSONObject reqParam = new JSONObject();
        reqParam.put("intent","config.auth.getAuthCode");
        return getString(data, reqParam);
    }

    private String getString(JSONObject data, JSONObject reqParam) {
        reqParam.put("data",data);

        JSONObject result = new JSONObject();
        result.put("appId", HttpConnect.appId);
        result.put("url",apiUrl);
        result.put("requestParams",reqParam);
        result.put("useServer", 1);
        return result.toString();
    }
}
