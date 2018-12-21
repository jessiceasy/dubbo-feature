package com.lww.featureagent;

import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.net.URL;
import java.security.ProtectionDomain;

/**
 * author: liweiwei
 * Date: 2018/12/20
 */
public class AbstractClusterInvokerTransformer implements ClassFileTransformer {

    public  byte[] getBytesFromFile()  {
        InputStream is = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("AbstractClusterInvoker.replace");
            System.out.println("替换文件路径:"+resource.getFile());
            is = getClass().getResourceAsStream( "/AbstractClusterInvoker.replace" ); // 可以正常使用
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = is.read(buffer))) {
                output.write(buffer, 0, n);
            }
            byte[] bytes = output.toByteArray();


            is.close();
            System.out.println("替换AbstractClusterInvoker成功");
            return bytes;
        } catch (Exception e) {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return null;
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public byte[] transform(ClassLoader l, String className, Class<?> c,
                            ProtectionDomain pd, byte[] b) throws IllegalClassFormatException {
        if (!className.equals("com/alibaba/dubbo/rpc/cluster/support/AbstractClusterInvoker")) {
            return null;
        }
        System.out.println("替换AbstractClusterInvoker");
        return getBytesFromFile();

    }

}
