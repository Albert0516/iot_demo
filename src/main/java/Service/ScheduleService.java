package Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleService {
    private static ScheduledExecutorService scheduler;

    public void init(){
        scheduler = Executors.newScheduledThreadPool(10);
    }

    public void checkTemperature(){

        init();
        long initialDelay = 5;
        long period = 5;

        DataMonitorService dm_service = new DataMonitorService();
        scheduler.scheduleWithFixedDelay(dm_service,initialDelay,period, TimeUnit.SECONDS);
    }
}
