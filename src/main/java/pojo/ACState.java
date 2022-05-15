package pojo;

public class ACState {
    public int isOn;

    String acID = "ir.974072506284359680";

    public String getAcId(){
        return acID;
    }

    public void SetAcId(String did){
        acID = did;
    }

    int temperature;

    int mode;

    int speed;

    public ACState(){
        isOn=1;
        temperature = 26;
        mode = 0;
        speed=0;
    }

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

    private String getOpenKey(){
        return "P"+isOn+"_M"+mode+"_T"+temperature+"_S"+speed;
    }

    public String SwitchState(){
        isOn = (isOn+1)%2;
        return getOpenKey();
    }

    public String On(){
        isOn = 0;
        return getOpenKey();
    }

    public String Off(){
        isOn=1;
        return getOpenKey();
    }

    public String tempUp(){
        if(temperature<31)
            temperature+=1;
        return getOpenKey();
    }

    public String tempDown(){
        if(temperature>17)
            temperature-=1;
        return getOpenKey();
    }

    public String setTemperature(int temp){
        temperature = temp;
        return getOpenKey();
    }


}
