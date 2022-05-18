# iot-demo

### 功能简介：

用于测试绿米物联网Aqara平台的以下功能：
1.传感器数据监测功能；
2.控制器的控制功能；
3.传感器与控制器的联动功能；

## 技术文档

[Aqara开发者平台](https://developer.aqara.com/)

[Aqara开发文档](https://opendoc.aqara.cn/docs/%E4%BA%91%E5%AF%B9%E6%8E%A5%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C/API%E6%96%87%E6%A1%A3.html)


### 模块说明


| 查询模式           | 说明                                   | 备注             |
|--------------------|----------------------------------------|------------------|
| HttpConnect        | 对接Aqara-API的请求接口工具类 |    |
| Login              | 调用Aqara开发者平台登录接口，登录成功后获取AccessToken,从而进行后续API调用操作 |    |
| AccessAuthorize    | 对接Aqara授权管理接口，包含获取手机验证码及访问令牌所需的接口 |    |
| ScheduleService    | 负责处理定时任务，开启DataMonitorService线程（包含周期性执行温度数据查询，当满足一定条件时控制空调） |    |
| AutoControl        | 对接自动控制-设备联动接口| 接口待完善（可通过Aqara-Home APP设置，无需开发即可达到自动控制效果（空调除外））   |
| DataMonitorService | 供ScheduleService调用的线程 - 线程内监听传感器温度数据，并根据需求中的温度条件和时间条件，控制空调  |    |
| RayDeviceCategoryService | 查询红外设备类型列表| 红外设备管理接口-子接口   |
| DeviceBrandService | 查询设备品牌列表| 红外设备管理接口-子接口   |
| DeviceQuery | 对接设备管理接口，根据设备id查询设备详细信息|    |
| LightControl | LED灯控制接口|    |


### 调用方式：

接口调用样例
```java

/**
* 0.通过HTTP协议调用Aqara API
* @param urlParam
* @param requestType
* @param requestJsonStr
* @param needToken
*/
HttpConnect.sendPostRequest(String requestUrl, String requestJsonStr);
//或
HttpConnect.sendRequest(String urlParam, String requestType, String requestJsonStr, boolean needToken)；

/**
* 1.登录(Login类中静态存储了登录账号/密码)
* @return
*/
Login.sendLoginRequest();

/**
* 2.查询设备列表
* @param devIds
* @return
*/
DeviceQuery.QueryDeviceInfo(List<String> devIds);

/**
* 3.操作灯泡-开/关灯(切换灯的状态)
* @return
*/

LightControl.OperateLight();

/**
* 4.请求传感器数据（requestType = Weather.HUMIDITY-温度 or Weather.TEMPERATURE-湿度）
* @param requestType
* @return
*/
Weather.requestData(Weather.HUMIDITY);


/**
* 5.查询支持的空调品牌列表
* @param categoryId
* @return
*/
DeviceBrandService.queryForDevCategories(String categoryId);

/**
* 6.新增遥控器
* @param dto
* @return
*/
ACControllerService.createIrController(ACIconAddDto dto);

/**
* 7.单机遥控器按键-促发特定操作（可操控有状态遥控器）
* @param requestDto
* @param hasState
* @return
*/
ACControllerService.clickButton(IrControllerClickButton requestDto, boolean hasState);
```




