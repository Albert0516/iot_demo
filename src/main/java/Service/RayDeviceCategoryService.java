package Service;

import Util.HttpConnect;
import com.alibaba.fastjson.JSONObject;
import dto.DeviceCategoryInfo;
import dto.DeviceCategoryQueryResult;

public class RayDeviceCategoryService {

    static final String intent = "query.ir.categories";

    public static String queryForDevCategories(){
        String jsonStr = getRequestStr();
        String responseStr = HttpConnect.sendRequest(HttpConnect.consoleDebugApi,"POST",jsonStr,true);
        readFromJsonStr(responseStr);
        return "success";
    }

    private static String getRequestStr()
    {
        JSONObject data = new JSONObject();
        JSONObject requestParams = new JSONObject();
        requestParams.put("intent",intent);
        requestParams.put("data",data);

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("appId",HttpConnect.appId);
        jsonParam.put("url",HttpConnect.urlApi);
        jsonParam.put("requestParams",requestParams);
        jsonParam.put("useServer",1);
        jsonParam.put("accessToken",HttpConnect.accessToken);

        return jsonParam.toString();
    }

    public static void readFromJsonStr(String jsonStr) {
        DeviceCategoryQueryResult dcqr = JSONObject.parseObject(jsonStr, DeviceCategoryQueryResult.class);
        for(int i=0;i<dcqr.devCategoryInfos.size();i++){
            DeviceCategoryInfo dci = dcqr.devCategoryInfos.get(i);
            System.out.println("categoryId:"+dci.categoryId);
            System.out.println("iconId:"+dci.iconId);
            System.out.println("enName"+dci.enName);
            System.out.println("model"+dci.model);
        }

    }
}
