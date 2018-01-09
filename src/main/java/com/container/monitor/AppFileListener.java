package com.container.monitor;

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
        System.out.println(file.getName()+":"+msg);
    }


}
