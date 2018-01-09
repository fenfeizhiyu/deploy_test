package com.container.load;

import com.container.core.Constant;

import java.io.*;

/**
 * @author yu.yang
 */
public class ContainerClassLoader extends ClassLoader{


    /**
     * .class文件扩展名
     */
    private final static String FILE_TYPE = ".class";
    /**
     * 加载路径
     */
    private  final static String PATH = "./src/main/java";

    public ContainerClassLoader(ClassLoader parent){
        super(parent);
    }




    /**
     * 读取class文件作为二进制流放入到byte数组中去
     * @param name
     * @return
     */
    protected byte[] loadClassData(String name,String path){
        InputStream in = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            name = name.replace(".", "\\");
            in = new BufferedInputStream(new FileInputStream(new File(path
                    +"\\"+ name + FILE_TYPE)));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = in.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name, PATH);
        return this.defineClass(name, data, 0, data.length);
    }
}
