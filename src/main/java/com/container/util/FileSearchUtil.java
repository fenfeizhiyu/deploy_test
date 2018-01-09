package com.container.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author yu.yang
 */
public class FileSearchUtil {



    public static Properties loadProperties(String path){
        Properties properties=new Properties();
        try {
            properties.load(new FileInputStream(path));
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }





}
