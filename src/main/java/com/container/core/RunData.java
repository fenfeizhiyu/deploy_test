package com.container.core;

import com.container.load.ContainerClassLoader;
import com.container.model.AppModel;
import com.container.model.AppRunData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu.yang
 */
public class RunData {

    public static List<AppModel> apps;

    public static List<Thread> ths=new ArrayList<Thread>();

    public static ContainerClassLoader containerClassLoader;


    public static Map<String, AppRunData> map = new HashMap<String, AppRunData>();





}
