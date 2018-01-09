package com.container.core;

import com.container.monitor.AppFileListener;
import com.container.monitor.AppFileMonitor;

/**
 * @author yu.yang
 */
public class ReloadProcess implements Runnable{


    public void run() {
        try {
            AppFileMonitor appFileMonitor=new AppFileMonitor(500L);
            appFileMonitor.monitor(Constant.APP_LOAD_PATH,new AppFileListener());
            appFileMonitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
