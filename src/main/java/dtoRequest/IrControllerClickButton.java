package dtoRequest;

import Util.Global;

public class IrControllerClickButton {
    public String devId;
    public int brandId;
    public int controllerId;
    public String keyId;

    public IrControllerClickButton(){
        this("1");
    }

    public IrControllerClickButton(String did){
        this(did,"1");
    }

    public IrControllerClickButton(String did,String keyId){
        this.keyId = keyId;
        devId = did;
        brandId = 182;
        controllerId = 11732;
    }
}
