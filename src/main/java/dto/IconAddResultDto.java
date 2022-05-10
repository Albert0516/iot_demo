package dto;

import com.alibaba.fastjson.annotation.JSONField;

public class IconAddResultDto extends BaseDto{
    @JSONField(name = "result")
    public DeviceId device;
}
