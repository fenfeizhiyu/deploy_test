package com.container.load;

/**
 * @author yu.yang
 */
public class AppClassLoader extends ContainerClassLoader {


    /**
     * 加载路径
     */
    private String path = "";

    public AppClassLoader(ClassLoader parent,String path){
        super(parent);
        this.path=path;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = super.loadClassData(name,path);
        return this.defineClass(name, data, 0, data.length);
    }
}
