package com.container.core;


import com.container.load.AppClassLoader;
import com.container.load.ContainerClassLoader;
import com.container.model.AppModel;
import com.container.model.AppRunData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 容器流程
 * @author yu.yang
 */
public class ContainerProcess {


    /**
     * 容器初始化.初始化类加载器
     */
   static void init(){
       RunData.containerClassLoader = new ContainerClassLoader(ContainerProcess.class.getClassLoader());
    }


    /**
     * 启动app
     */
    public void start(){
        RunData.apps=getAppModels();
        initAppThread();
        for(Thread th:RunData.ths){
            th.start();
        }
    }

    /**
     * 重新加载指定app
     */
    public void reLoadApp(AppModel appModel){
        doInitAppThread(appModel).start();
    }

    /**
     * 卸载指定app
     */
    public void unLoadApp(String appName){
        AppRunData appRunData=RunData.getAppRunData(appName);
        //关闭循环开关
        appRunData.start =false;
    }




    /**
     * 初始化app线程
     */
    public void initAppThread(){
        if(RunData.apps.size()>0){
            for(AppModel app:RunData.apps){
                doInitAppThread(app);
            }
        }else {
            System.out.println("no app run");
        }
    }

    private Thread doInitAppThread(AppModel app) {
        AppProcess appProcess = new AppProcess(app.getAppPath(), app.getAppName());
        Thread th = new Thread(appProcess);
        th.setName("app:" + app.getAppName());
        AppClassLoader appClassLoader = new AppClassLoader(RunData.containerClassLoader,app.getAppPath());
        th.setContextClassLoader(appClassLoader);
        RunData.ths.add(th);
        AppRunData appRunData = new AppRunData();
        appRunData.setAppModel(app);
        appRunData.setThread(th);
        RunData.addDataMap(app.getAppName(),appRunData);
        return th;
    }

    /**
     * 加载指定路径的app
     * @param
     * @return
     */
    public AppModel getAppModelByPath(File dir){
        if(dir.isDirectory()){
            AppModel am = new AppModel();
            am.setAppName(dir.getName());
            am.setAppPath(dir.getPath());
            return am;
        }else {
            return null;
        }

    }



    /**
     * 扫描指定文件目录,启动App
     */
    public List<AppModel> getAppModels(){
        List<AppModel> modes = new ArrayList<AppModel>();
        try {
            File appDir=new File(Constant.APP_LOAD_PATH);
            for(File f:appDir.listFiles()){
                AppModel appModel=getAppModelByPath(f);
                if(appModel!=null){
                    modes.add(appModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modes;
    }

    public static void main(String[] args){
        String path = "E:\\workCodeLib\\deploy_test\\src\\main\\java\\com\\container\\test";
        File file=new File(path);
        File[] fs=file.listFiles();
        for(File f:fs){
            if(f.isDirectory()){
                System.out.println(f.getPath());
            }
        }
    }

}
