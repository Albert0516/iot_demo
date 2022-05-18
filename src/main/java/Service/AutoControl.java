package Service;

import Util.HttpConnect;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 自动化控制
 */
public class AutoControl {

    static final String intentCreate = "config.linkage.create";

    /**
     * 创建自动化控制
     * 该过程全部操作可在Aqara Home中设置（即无需开发即可实现功能）
     * @return
     */
    public static String CreateLinkage(){

        String jsonStr = getRequestStr();
        return HttpConnect.sendPostRequest(HttpConnect.consoleDebugApi, jsonStr);
    }

    //TODO-待完善(参数对象未设置)
    private static String getRequestStr(){

        JSONObject params = new JSONObject();
        params.put("paramType","");
        params.put("paramUnit","");
        params.put("paramId","");
        params.put("value","");

        JSONObject con = new JSONObject();
        con.put("triggerDefinitionId","");
        con.put("model","");
        con.put("beginTime","");
        con.put("endTime","");
        con.put("params","");
        con.put("subjectId","");

        JSONArray conditionList = new JSONArray();
        conditionList.add(con);

        JSONObject conditions = new JSONObject();
        conditions.put("condition",conditionList);
        conditions.put("relation","");

        JSONObject act = new JSONObject();
        act.put("delayTimeUnit","");
        act.put("actionDefinitionId","");
        act.put("model","");
        act.put("delayTime","");
        act.put("subjectId","");

        JSONArray actionList = new JSONArray();
        actionList.add(act);

        JSONObject actions = new JSONObject();
        actions.put("action",actionList);

        JSONObject data = new JSONObject();
        data.put("name","");
        data.put("positionId","");
        data.put("conditions",conditions);
        data.put("actions",actions);

        JSONObject result = new JSONObject();
        result.put("intent",intentCreate);
        result.put("data",data);

        return result.toString();
    }
}
