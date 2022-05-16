package Service;

import AirConditioner.ACControllerService;
import Sensor.Weather;
import Util.Global;
import com.alibaba.fastjson.JSONObject;
import dto.Temperature;
import dto.TemperatureResult;
import dtoRequest.IrControllerClickButton;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 传感器数据监测逻辑 - 温度
 */
public class DataMonitorService implements Runnable{
    @Override
    public void run() {
        //System.out.println("test for schedule service-current time:"+System.currentTimeMillis());
        String result = Weather.requestData(Weather.TEMPERATURE);
        //System.out.println(result);
        TemperatureResult tempResult = JSONObject.parseObject(result, TemperatureResult.class);
        if(tempResult.tempObj==null||tempResult.tempObj.size() ==0) return;
        Temperature temp = tempResult.tempObj.get(0);
        double tempVal = getTempVal(temp.tempVal);
        System.out.println("传感器上一次监测时间："+Date2String(temp.time)+", 温度值："+tempVal);
        if(tempVal>26){
            if(Global.acState.isOn==1){
                ACControllerService.clickButton(new IrControllerClickButton(null,"1"),true);
            }
        }
        if(tempVal<25){
            if(Global.acState.isOn==0){
                ACControllerService.clickButton(new IrControllerClickButton(null,"2"),true);
            }
        }
    }


    public static String Date2String(String timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = Long.parseLong(timestamp);
        Date date = new Date(time);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static double getTempVal(String valStr){
        int temp = Integer.parseInt(valStr);
        return Math.round(temp/100.0);
    }
}
