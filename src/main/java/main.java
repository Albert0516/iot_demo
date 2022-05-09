import Sensor.Wheather;
import Service.DeviceBrandService;
import Service.LightControl;
import Service.Login;
import Service.RayDeviceCategoryService;

import java.util.Scanner;

public class main {


    public static void main(String[] args) {

        System.out.println("请输出指令以进行相关操作:");
        System.out.println("0-登录并获取token");
        System.out.println("1-查询设备列表");
        System.out.println("2-LED灯开/关");
        System.out.println("3-查询气温");
        System.out.println("4-查询适度");
        System.out.println("5-查询紅外设备类型（列表）");
        System.out.println("6-查询支持的空调品牌");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine())
        {
            String input = scanner.nextLine();
            System.out.println("received input:"+input);
            try{
                int code = Integer.parseInt(input);
                switch (code){
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
                    default:break;
                }
            }catch (Exception e){

            }
       }
    }


}
