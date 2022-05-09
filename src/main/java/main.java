import Sensor.Wheather;

import java.util.Scanner;

public class main {


    public static void main(String[] args) {

        System.out.println("请输出指令以进行相关操作:");
        System.out.println("0-登录并获取token");
        System.out.println("1-查询设备列表");
        System.out.println("2-LED灯开/关");
        System.out.println("3-查询气温");
        System.out.println("4-查询适度");
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
                        System.out.println(DeviceQuery.QueryDeviceInfo());break;
                    case 2:
                        System.out.println(LightControl.OperateLight());break;
                    case 3:
                        System.out.println(Wheather.requestData(Wheather.TEMPERATURE));break;
                    case 4:
                        System.out.println(Wheather.requestData(Wheather.HUMIDITY));break;
                    default:break;
                }
            }catch (Exception e){

            }
        }
    }


}
