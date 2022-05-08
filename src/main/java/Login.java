import Util.HttpConnect;
import com.alibaba.fastjson.JSONObject;

public class Login {

    static String aqaraID = "13129092636";

    private static String userAccount = "863007116@qq.com";
    private static String psdEncoded = "96d260e2e630efa27a5176f676aa29d8";
    private static String urlParam = "https://developer.aqara.com/open-server/developer/login";

    public static String sendLoginRequest() {
        String jsonStr = getRequestStr();
        String result = HttpConnect.sendRequest(urlParam, "POST", jsonStr,false);
        return result;
    }

    private static String getRequestStr()
    {
        JSONObject loginParams = new JSONObject();
        loginParams.put("account",userAccount);
        loginParams.put("password",psdEncoded);
        return loginParams.toString();
    }

}
