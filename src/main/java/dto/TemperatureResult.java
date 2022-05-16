package dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 返回值JsonStr的解析对象（温度值）
 */
public class TemperatureResult extends BaseDto{
    @JSONField(name = "result")
    public List<Temperature> tempObj;
}
