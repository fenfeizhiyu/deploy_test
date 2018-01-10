package com.container.core;

import com.container.load.AppClassLoader;
import com.container.model.AppRunData;
import com.container.monitor.AppFileListener;
import com.container.monitor.AppFileMonitor;

import java.io.File;

public class AppReload {

    public static void startAppMonitor(String path){
        try {
            AppFileMonitor appFileMonitor=new AppFileMonitor(5000L);
            appFileMonitor.monitor(path,new AppFileListener());
            appFileMonitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void reloadApp(String appName){
        AppRunData appRunData=RunData.getAppRunData(appName);
        String path=appRunData.getAppModel().getAppPath();
        appRunData.getThread().setContextClassLoader(new AppClassLoader(RunData.containerClassLoader,path));
    }


    public static void reloadApp2(ContainerProcess containerProcess,String appName){
        containerProcess.unLoadApp(appName);
        AppRunData appRunData=RunData.getAppRunData(appName);
        containerProcess.reLoadApp(containerProcess.getAppModelByPath(new File(appRunData.getAppModel().getAppPath())));
    }
}
