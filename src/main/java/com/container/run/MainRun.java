package com.container.run;

import com.container.core.Constant;
import com.container.core.ContainerProcess;
import com.container.core.AppReload;
import com.container.core.RunData;

/**
 * @author yu.yang
 */
public class MainRun {

    public static void main(String[] args){
        RunData.containerProcess=new ContainerProcess();
        RunData.containerProcess.start();
        AppReload.startAppMonitor(Constant.APP_LOAD_PATH);
    }

}
