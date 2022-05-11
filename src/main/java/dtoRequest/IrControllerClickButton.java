package dtoRequest;

import Util.Global;

public class IrControllerClickButton {
    public String devId;
    public int brandId;
    public int controllerId;
    public String keyId;

    public IrControllerClickButton(){
        this("P0_M0_T26_S0");
    }

    public IrControllerClickButton(String did){
        this(did,"P0_M0_T26_S0");
    }

    public IrControllerClickButton(String did,String optId){
        int code = Integer.parseInt(optId);
        this.keyId = Global.acState.getOpenKey(code);
        devId = did;
        brandId = 182;
        controllerId = Global.brandId_Medea;
    }
}
