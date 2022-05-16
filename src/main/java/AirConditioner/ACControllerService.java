package AirConditioner;

import Util.Global;
import Util.HttpConnect;
import com.alibaba.fastjson.JSONObject;
import dtoRequest.ACIconAddDto;
import dto.IconAddResultDto;
import dtoRequest.IrControllerClickButton;

/**
 * 红外设备-空调控制逻辑
 */
public class ACControllerService {

    /**
     * 固定指令-查询遥控器按键（仅查询无状态遥控器）
     */
    static final String intentQuery = "query.ir.keys";

    /**
     * 固定指令-单击遥控器按键
     */
    static final String intentClickButton = "write.ir.click";

    /**
     * 固定指令-新增遥控器
     */
    static final String intentAdd = "config.ir.create";

    /**
     * 固定指令-删除遥控器
     */
    static final String intentDelete = "config.ir.delete";

    /**
     * 查询遥控器下的按键列表信息（仅无状态遥控器）
     * @param irControllerName
     * @return
     */
    public static String ButtonInfoQuery_IrController(String irControllerName){

        //String did = Global.ir_iconMap.get(iconName);
        String jsonStr = getRequestStrIconQuery(irControllerName);
        String result = HttpConnect.sendRequest(HttpConnect.consoleDebugApi,"POST",jsonStr,true);
        //TODO-解析按键信息
        return result;
    }

    /**
     * 单机遥控器按键-促发特定操作（可操控有状态遥控器）
     * @param requestDto
     * @param hasState
     * @return
     */
    public static String clickButton(IrControllerClickButton requestDto, boolean hasState){

        String jsonStr = getRequestStrClickButton(requestDto, hasState);
        String result = HttpConnect.sendPostRequest(HttpConnect.consoleDebugApi, jsonStr);
        return result;
    }

    /**
     * 新增遥控器
     * @param dto
     * @return
     */
    public static String createIrController(ACIconAddDto dto){

        String jsonStr = getRequestStrAdd(dto);
        String result = HttpConnect.sendPostRequest(HttpConnect.consoleDebugApi, jsonStr);
        //解析数返回结果-获取did
        IconAddResultDto resultDto = JSONObject.parseObject(result, IconAddResultDto.class);
        Global.ir_iconMap.put(dto.name, resultDto.device.id);
        return "success.devId is:"+resultDto.device.id;
    }

    /**
     * 删除遥控器
     * @param iconName
     * @return
     */
    public static String deleteIrController(String iconName){
        String did = Global.ir_iconMap.get(iconName);
        String jsonStr = getRequestStrDelete(did);
        return HttpConnect.sendPostRequest(HttpConnect.consoleDebugApi, jsonStr);
    }


    /**
     * 根据传入的请求对象，封装aqaraApi请求数据 - 单击按键
     * @param requestDto
     * @param hasState
     * @return
     */
    private static String getRequestStrClickButton(IrControllerClickButton requestDto, boolean hasState){
        JSONObject data = new JSONObject();
        data.put("did",requestDto.devId);
        data.put("brandId",requestDto.brandId);
        data.put("controllerId",requestDto.controllerId);
        String keyId = hasState?"acKey":"keyId";
        data.put(keyId,requestDto.keyId);
        return HttpConnect.packageRequestData(data, intentClickButton,true);
    }

    /**
     * 根据传入的请求对象，封装aqaraApi请求数据 - 查询按键列表
     * @param did
     * @return
     */
    private static String getRequestStrIconQuery(String did)
    {
        JSONObject data = new JSONObject();
        data.put("did", did);
        return HttpConnect.packageRequestData(data,intentQuery,true);
    }

    /**
     * 根据传入的请求对象，封装aqaraApi请求数据 - 新增遥控器
     * @param iconDto
     * @return
     */
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

    /**
     * 根据传入的请求对象，封装aqaraApi请求数据 - 删除遥控器
     * @param did
     * @return
     */
    private static String getRequestStrDelete(String did)
    {
        JSONObject data = new JSONObject();
        data.put("did", did);
        return HttpConnect.packageRequestData(data,intentDelete,true);
    }
}
