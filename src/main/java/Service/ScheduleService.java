package Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务
 */
public class ScheduleService {
    private static ScheduledExecutorService scheduler;

    public void init(){
        scheduler = Executors.newScheduledThreadPool(10);
    }

    /**
     * 启动定时任务-获取传感器温度数据
     */
    public void checkTemperature(){

        init();
        long initialDelay = 5*12;
        long period = 5*60;

        //
        DataMonitorService dm_service = new DataMonitorService();
        scheduler.scheduleWithFixedDelay(dm_service,initialDelay,period, TimeUnit.SECONDS);
    }
}
