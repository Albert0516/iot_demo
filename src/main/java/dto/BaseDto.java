package dto;

import com.alibaba.fastjson.annotation.JSONField;

public class BaseDto {

    @JSONField(name = "code")
    public int code;

    @JSONField(name = "message")
    public String message;

    @JSONField(name = "msgDetails")
    public String msgDetails;

    @JSONField(name = "requestId")
    public String requestId;
}
