package Service;

import Util.HttpConnect;
import com.alibaba.fastjson.JSONObject;

/**
 * 查询设备品牌列表
 */
public class DeviceBrandService {

    static final String intent = "query.ir.brands";

    /**
     * 查询空调品牌列表
     */
    public static String queryForDevCategories(String categoryId){
        try{
            int cid = Integer.parseInt(categoryId);
            String jsonStr = getRequestStr(cid);
            String responseStr = HttpConnect.sendPostRequest(HttpConnect.consoleDebugApi, jsonStr);
            return responseStr;
        }catch (Exception e){
            e.printStackTrace();
        }
       return "request failed...";
    }

    private static String getRequestStr(int categoryId)
    {
        JSONObject data = new JSONObject();
        data.put("categoryId",categoryId);

        return HttpConnect.packageRequestData(data,intent,true);
    }
}
