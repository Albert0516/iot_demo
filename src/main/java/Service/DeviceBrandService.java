package Service;

import Util.HttpConnect;
import com.alibaba.fastjson.JSONObject;

public class DeviceBrandService {

    static final String intent = "query.ir.brands";

    public static String queryForDevCategories(){
        String jsonStr = getRequestStr(5);
        String responseStr = HttpConnect.sendRequest(HttpConnect.consoleDebugApi,"POST", jsonStr,true);
        return responseStr;
    }

    private static String getRequestStr(int categoryId)
    {
        JSONObject data = new JSONObject();
        data.put("categoryId",categoryId);

        return HttpConnect.packageRequestData(data,intent,true);
    }
}
