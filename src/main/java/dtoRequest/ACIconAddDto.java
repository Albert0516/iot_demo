package dtoRequest;

import Util.Global;

public class ACIconAddDto {
    public int brandId;
    public int categoryId;
    public String parentDevId;
    public int controllerId;
    public String name;

    public ACIconAddDto(){
        brandId = 182;
        categoryId = 5;
        controllerId = Global.brandId_Medea;
    }

    public ACIconAddDto(String devId, String name){
        this();
        parentDevId = devId;
        this.name = name;

    }

    public ACIconAddDto(String name){
        this(Global.M2_devId, name);
    }

}
