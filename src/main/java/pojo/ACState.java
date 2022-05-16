package pojo;

/**
 * 空调状态内存对象
 */
public class ACState {
    /**
     * 空调开启状态
     * 0-开启；1-关闭
     */
    public int isOn;

    /**
     * 空调对应的遥控控器虚拟id
     */
    String acID = "ir.974072506284359680";

    public String getAcId(){
        return acID;
    }

    public void SetAcId(String did){
        acID = did;
    }

    /**
     * 空调当前温度
     */
    int temperature;

    /**
     * 空调当前工作模式
     */
    int mode;

    /**
     * 风速
     */
    int speed;

    public ACState(){
        isOn=1;
        temperature = 26;
        mode = 0;
        speed=0;
    }

    /**
     * 设置遥控器指令
     * @return
     */
    public String getOpenKey(int code){
        switch (code){
            case 1: isOn = 0;break;
            case 2: isOn = 1;break;
            case 3:
                if(temperature<31)
                    temperature+=1;
                break;
            case 4:
                if(temperature>17)
                    temperature-=1;
                break;
            //case 5:temperature = targetTemp;break;
            default:break;
        }
        return getOpenKey();
    }

    /**
     * 设置AqaraApi所需的请求指令（有状态遥控器）
     * @return
     */
    private String getOpenKey(){
        return "P"+isOn+"_M"+mode+"_T"+temperature+"_S"+speed;
    }

}
