import AirConditioner.ACControllerService;
import Sensor.Wheather;
import Service.*;

import java.util.List;
import java.util.Scanner;

public class main {


    public static void main(String[] args) {

        ScheduleService scheduler = new ScheduleService();
        scheduler.checkTemperature();

        System.out.println("请输出指令以进行相关操作:");
        System.out.println("0-登录并获取token");
        System.out.println("1-查询设备列表");
        System.out.println("2-LED灯开/关");
        System.out.println("3-查询气温");
        System.out.println("4-查询适度");
        System.out.println("5-查询紅外设备类型（列表）");
        System.out.println("6-查询支持的空调品牌");
        System.out.println("7-查询遥控器按键信息");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine())
        {
            String input = scanner.nextLine();
            System.out.println("received input:"+input);
            try{
                ParamInput dataInput = handleInput(input);
                switch (dataInput.code){
                    case 0:
                        System.out.println(Login.sendLoginRequest());break;
                    case 1:
                        System.out.println(Service.DeviceQuery.QueryDeviceInfo());break;
                    case 2:
                        System.out.println(LightControl.OperateLight());break;
                    case 3:
                        System.out.println(Wheather.requestData(Wheather.TEMPERATURE));break;
                    case 4:
                        System.out.println(Wheather.requestData(Wheather.HUMIDITY));break;
                    case 5:
                        RayDeviceCategoryService.queryForDevCategories();break;
                    case 6:
                        System.out.println(DeviceBrandService.queryForDevCategories());break;
                    case 7:
                        String did_test = "ir.973750669297958912";
                        System.out.println(ACControllerService.ButtonInfoQuery_IrController(did_test));break;
                    default:break;
                }
            }catch (Exception e){

            }
       }
    }

    public static ParamInput handleInput(String lineParams){
        String[] params = lineParams.split(" ");
        if(params.length==0) throw new RuntimeException();
        ParamInput result = new ParamInput();
        try{
            int code = Integer.parseInt(params[0]);
            result.code = code;
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
