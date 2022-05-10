package dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class DeviceCategoryQueryResult extends BaseDto {

    @JSONField(name = "name")
    public String name;

    @JSONField(name = "result")
    public List<DeviceCategoryInfo> devCategoryInfos;
}
