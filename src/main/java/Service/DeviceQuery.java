package Service;

import Util.Global;
import Util.HttpConnect;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询设备信息
 */
public class DeviceQuery {

    static final String intentQueryDevInfo = "query.device.info";
    public static String QueryDeviceInfo(List<String> devIds)
    {
        if(devIds.size()==0) throw new RuntimeException("请求的设备列表为空！");
        String jsonStr = getRequestStr(devIds);
        return HttpConnect.sendPostRequest(HttpConnect.consoleDebugApi, jsonStr);
    }

    private static String getRequestStr(List<String> devIds)
    {
        JSONArray deviceIds = new JSONArray();
        for(String devId:devIds)
            deviceIds.add(devId);

        JSONObject data = new JSONObject();
        data.put("dids",deviceIds);
        data.put("positionId","");
        data.put("pageNum", 1);
        data.put("pageSize", 10);

        return HttpConnect.packageRequestData(data,intentQueryDevInfo,true);
    }
}
