package dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class DeviceCategoryQueryResult {

    @JSONField(name = "code")
    public int code;

    @JSONField(name = "requestId")
    public String requestId;

    @JSONField(name = "message")
    public String message;

    @JSONField(name = "name")
    public String name;

    @JSONField(name = "msgDetails")
    public String msgDetails;

    @JSONField(name = "result")
    public List<DeviceCategoryInfo> devCategoryInfos;
}
