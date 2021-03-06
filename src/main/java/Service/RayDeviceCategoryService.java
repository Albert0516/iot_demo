package Service;

import Util.HttpConnect;
import com.alibaba.fastjson.JSONObject;
import dto.DeviceCategoryInfo;
import dto.DeviceCategoryQueryResult;

public class RayDeviceCategoryService {

    static final String intent = "query.ir.categories";

    /**
     * 查询空外设备类型（列表）
     * @return
     */
    public static String queryForDevCategories(){
        String jsonStr = getRequestStr();
        String responseStr = HttpConnect.sendPostRequest(HttpConnect.consoleDebugApi,jsonStr);
        readFromJsonStr(responseStr);
        return "success";
    }

    private static String getRequestStr()
    {
        return HttpConnect.packageRequestData(new JSONObject(), intent,true);
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
