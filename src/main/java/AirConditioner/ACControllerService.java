package AirConditioner;

import Util.Global;
import Util.HttpConnect;
import com.alibaba.fastjson.JSONObject;
import dtoRequest.ACIconAddDto;
import dto.IconAddResultDto;
import dtoRequest.IrControllerClickButton;

public class ACControllerService {


    static final String intentQuery = "query.ir.keys";
    static final String intentClickButton = "write.ir.click";
    static final String intentAdd = "config.ir.create";
    static final String intentDelete = "config.ir.delete";

    public static String ButtonInfoQuery_IrController(String iconName){

        //String did = Global.ir_iconMap.get(iconName);
        String jsonStr = getRequestStrIconQuery(iconName);
        String result = HttpConnect.sendRequest(HttpConnect.consoleDebugApi,"POST",jsonStr,true);
        //TODO-解析按键信息
        return result;
    }

    public static String clickButton(IrControllerClickButton requestDto){

        String jsonStr = getRequestStrClickButton(requestDto);
        String result = HttpConnect.sendRequest(HttpConnect.consoleDebugApi,"POST",jsonStr,true);
        return result;
    }

    public static String addIrController(ACIconAddDto dto){

        String jsonStr = getRequestStrAdd(dto);
        String result = HttpConnect.sendRequest(HttpConnect.consoleDebugApi,"POST",jsonStr,true);
        //解析数返回结果-获取did
        IconAddResultDto resultDto = JSONObject.parseObject(result, IconAddResultDto.class);
        Global.ir_iconMap.put(dto.name, resultDto.device.id);
        return "success";
    }

    public static String deleteIrController(String iconName){
        String did = Global.ir_iconMap.get(iconName);
        String jsonStr = getRequestStrDelete(did);
        return HttpConnect.sendRequest(HttpConnect.consoleDebugApi,"POST",jsonStr,true);
    }


    private static String getRequestStrClickButton(IrControllerClickButton requestDto){
        JSONObject data = new JSONObject();
        data.put("did",requestDto.devId);
        data.put("brandId",requestDto.brandId);
        data.put("controllerId",requestDto.controllerId);
        data.put("keyId",requestDto.keyId);
        return HttpConnect.packageRequestData(data, intentClickButton,true);
    }

    private static String getRequestStrIconQuery(String did)
    {
        JSONObject data = new JSONObject();
        data.put("did", did);
        return HttpConnect.packageRequestData(data,intentQuery,true);
    }

    private static String getRequestStrAdd(ACIconAddDto iconDto)
    {
        JSONObject data = new JSONObject();
        data.put("brandId", iconDto.brandId);
        data.put("categoryId",iconDto.categoryId);
        data.put("name",iconDto.name);
        data.put("parentDid",iconDto.parentDevId);
        data.put("controllerId",iconDto.controllerId);

        return HttpConnect.packageRequestData(data,intentAdd,true);
    }

    private static String getRequestStrDelete(String did)
    {
        JSONObject data = new JSONObject();
        data.put("did", did);
        return HttpConnect.packageRequestData(data,intentDelete,true);
    }
}
