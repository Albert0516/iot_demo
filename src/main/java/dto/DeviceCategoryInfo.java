package dto;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceCategoryInfo {
    @JSONField(name = "iconId")
    public String iconId;

    @JSONField(name = "enName")
    public String enName;

    @JSONField(name = "name")
    public String name;

    @JSONField(name = "model")
    public String model;

    @JSONField(name = "categoryId")
    public int categoryId;
}
