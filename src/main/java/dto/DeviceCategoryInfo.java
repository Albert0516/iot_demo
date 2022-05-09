package dto;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceCategoryInfo {
    @JSONField(name = "iconId")
    String iconId;

    @JSONField(name = "enName")
    String enName;

    @JSONField(name = "name")
    String name;

    @JSONField(name = "model")
    String model;

    @JSONField(name = "categoryId")
    int categoryId;
}
