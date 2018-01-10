package com.container.monitor;

import com.container.core.AppReload;
import com.container.core.RunData;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * @author yu.yang
 */
public class AppFileListener implements FileAlterationListener {

    public void onStart(FileAlterationObserver observer) {

    }

    public void onDirectoryCreate(File directory) {
        printEvent(directory,"dic create");
    }

    public void onDirectoryChange(File directory) {
        printEvent(directory,"dic change");
    }

    public void onDirectoryDelete(File directory) {
        printEvent(directory,"dic delete");
    }

    public void onFileCreate(File file) {
       // printEvent(file,"create");
    }

    public void onFileChange(File file) {
      //  printEvent(file,"change");
    }

    public void onFileDelete(File file) {
        //printEvent(file,"delete");
    }

    public void onStop(FileAlterationObserver observer) {
        //System.out.println("stoped");
    }

    private void printEvent(File file,String msg){
        //AppReload.reloadApp(file.getName());
        AppReload.reloadApp2(RunData.containerProcess,file.getName());
        System.out.println(file.getName()+":"+msg);
    }




}
