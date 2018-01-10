package com.container.model;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author yu.yang
 */
public class AppRunData {

    private Thread thread;

    private AppModel appModel;

    public  volatile boolean start =true;


    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public AppModel getAppModel() {
        return appModel;
    }

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }


}
