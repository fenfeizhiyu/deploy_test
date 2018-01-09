package com.container.model;

/**
 * @author yu.yang
 */
public class AppRunData {

    private Thread thread;

    private AppModel appModel;


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
