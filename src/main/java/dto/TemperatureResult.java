package dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class TemperatureResult extends BaseDto{
    @JSONField(name = "result")
    public List<Temperature> tempObj;
}
