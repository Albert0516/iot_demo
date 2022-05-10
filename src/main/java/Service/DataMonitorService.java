package Service;

public class DataMonitorService implements Runnable{
    @Override
    public void run() {
        System.out.println("test for schedule service-current time:"+System.currentTimeMillis());
    }
}
