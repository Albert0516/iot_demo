package Service;

import Util.HttpConnect;
import com.alibaba.fastjson.JSONObject;

public class Login {

    /**
     * 获取手机验证码所需的账号
     */
    static String aqaraID = "13129092636";

    /**
     * Aqara开发者平台-账号
     */
    private static String userAccount = "863007116@qq.com";

    /**
     * Aqara开发者平台-密码（已加密）
     */
    private static String psdEncoded = "96d260e2e630efa27a5176f676aa29d8";

    /**
     * Aqara开发者平台-登录url
     */
    private static String urlParam = "https://developer.aqara.com/open-server/developer/login";

    /**
     * 请求登录
     * @return
     */
    public static String sendLoginRequest() {
        String jsonStr = getRequestStr();
        String result = HttpConnect.sendRequest(urlParam, "POST", jsonStr,false);
        return result;
    }

    /**
     * 封装登录请求数据-账号+密码
     * @return
     */
    private static String getRequestStr()
    {
        JSONObject loginParams = new JSONObject();
        loginParams.put("account",userAccount);
        loginParams.put("password",psdEncoded);
        return loginParams.toString();
    }

}
