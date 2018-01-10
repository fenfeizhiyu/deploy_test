package com.container.core;

import com.container.model.AppRunData;
import com.container.util.FileSearchUtil;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 *
 * 加载app中的主类，并执行主方法
 * @author yu.yang
 */
public class AppProcess  implements  Runnable{


    private String appPath;

    private String appName;

    public AppProcess(String appPath, String appName) {
        this.appPath = appPath;
        this.appName = appName;
    }

    public void run() {
        runApp(appPath);
    }

    /**
     * 执行app中的appRum方法
     * appPath app项目路径
     */
    public void runApp2(String appPath){

        Properties properties=FileSearchUtil.loadProperties(getAppPropertiesPath(appPath));
        String runClass=properties.getProperty(Constant.PROP_MAIN_METHOD_KEY);
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
        try{
            Class<?> clazz =  classLoader.loadClass(runClass);
            Object obj=clazz.newInstance();
            Method runMethod = clazz.getMethod(Constant.APP_MAIN_METHOD);
            if(runMethod==null){
                System.out.println("没有找到主方法");
            }
            while (true){
                runMethod.invoke(obj);
                sleep(1000L);
            }
        }catch (Exception e){

        }
    }

    public void runApp(String appPath){

        Properties properties=FileSearchUtil.loadProperties(getAppPropertiesPath(appPath));
        String runClass=properties.getProperty(Constant.PROP_MAIN_METHOD_KEY);
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
        try{
            Class<?> clazz =  classLoader.loadClass(runClass);
            Object obj=clazz.newInstance();
            Method runMethod = clazz.getMethod(Constant.APP_MAIN_METHOD);
            if(runMethod==null){
                System.out.println("没有找到主方法");
            }
            AppRunData appRunData=RunData.getAppRunData(this.appName);
            while (appRunData.start){
                runMethod.invoke(obj);
                sleep(1000L);
            }
        }catch (Exception e){

        }
    }


    public void  sleep(long time){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取app配置文件路径
     * @param appPath
     * @return
     */
    public String getAppPropertiesPath(String appPath){
        return appPath + "/"+Constant.PROP_FILE_NAME;
    }

}
