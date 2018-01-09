package com.container.monitor;

/**
 * @author yu.yang
 */
public class TM {


    public static void main(String[] args){
        String path = "D:\\app_test";
        try {
            AppFileMonitor appFileMonitor=new AppFileMonitor(500L);
            appFileMonitor.monitor(path,new AppFileListener());
            appFileMonitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
