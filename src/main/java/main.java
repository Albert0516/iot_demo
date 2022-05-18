import AirConditioner.ACControllerService;
import Sensor.Weather;
import Service.*;
import Util.HttpConnect;
import dtoRequest.ACIconAddDto;
import dtoRequest.IrControllerClickButton;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class main {


    public static void main(String[] args) {

        System.out.println(Login.sendLoginRequest());

        ScheduleService scheduler = new ScheduleService();
        scheduler.checkTemperature();

        String temp = Base64.getEncoder().encodeToString("852456ygy".getBytes(StandardCharsets.UTF_8));
        System.out.println(temp);

        System.out.println("请输出指令以进行相关操作:");
        System.out.println("1-查询设备列表");
        System.out.println("2-LED灯开/关");
        System.out.println("3-查询气温");
        System.out.println("4-查询湿度");
        System.out.println("5-查询紅外设备类型（列表）");
        System.out.println("6-查询支持的空调品牌");
        System.out.println("7-新增遥控器-[name]");
        System.out.println("8-按键-空调操作+[1-开空调;2-关空调;3-加一度;4-减一度]");
        System.out.println("10-查询遥控器按键信息-[devId]");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine())
        {
                String input = scanner.nextLine();
            System.out.println("received input:"+input);
            try{
                ParamInput dataInput = handleInput(input);
                switch (dataInput.code){
                    case 1:
                        if (dataInput.params==null||dataInput.params.size()==0) throw new RuntimeException();
                        System.out.println(Service.DeviceQuery.QueryDeviceInfo(dataInput.params));break;
                    case 2:
                        System.out.println(LightControl.OperateLight());break;
                    case 3:
                        System.out.println(Weather.requestData(Weather.TEMPERATURE));break;
                    case 4:
                        System.out.println(Weather.requestData(Weather.HUMIDITY));break;
                    case 5:
                        RayDeviceCategoryService.queryForDevCategories();break;
                    case 6:
                        if (dataInput.params==null||dataInput.params.size()==0) throw new RuntimeException();
                        System.out.println(DeviceBrandService.queryForDevCategories(dataInput.params.get(0)));break;
                    case 7:
                        if (dataInput.params==null||dataInput.params.size()==0) throw new RuntimeException();
                        System.out.println(ACControllerService.createIrController(new ACIconAddDto(dataInput.params.get(0))));break;
                    case 8:
                        if (dataInput.params==null||dataInput.params.size()<2) throw new RuntimeException();
                        String did = dataInput.params.get(0);
                        String code = dataInput.params.get(1);
                        System.out.println(ACControllerService.clickButton(new IrControllerClickButton(did,code),true));
                        break;
                    case 10:
                        if (dataInput.params==null||dataInput.params.size()==0) throw new RuntimeException();
                        System.out.println(ACControllerService.ButtonInfoQuery_IrController(dataInput.params.get(0)));break;
                    default:break;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
       }
    }


    public static ParamInput handleInput(String lineParams){
        String[] params = lineParams.split(" ");
        if(params.length==0) throw new RuntimeException();
        ParamInput result = new ParamInput();
        try{
            int code = Integer.parseInt(params[0]);
            List<String> paramList = new ArrayList<>(params.length-1);
            for (int i=1;i<params.length;i++)
                paramList.add(params[i]);
            result.code = code;
            result.params = paramList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }


}

class ParamInput{
    int code;

    List<String> params;
}
