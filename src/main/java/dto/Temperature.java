package dto;

import com.alibaba.fastjson.annotation.JSONField;

public class Temperature{
    @JSONField(name = "timeStamp")
    public String time;

    @JSONField(name = "resourceId")
    public String resId;

    @JSONField(name = "subjectId")
    public String devId;

    @JSONField(name = "value")
    public String tempVal;

}
