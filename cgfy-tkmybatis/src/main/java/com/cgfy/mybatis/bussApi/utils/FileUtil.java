package com.cgfy.mybatis.bussApi.utils;

import org.apache.commons.beanutils.BeanUtils;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class FileUtil {


    /**
     *
     * @Title: mapToObject
     * @Description: TODO(map转换为bean)
     * @return T    返回类型
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws Exception {
        if (map == null) {
            return null;
        }

        T obj = beanClass.newInstance();
        BeanUtils.populate(obj, map);

        return obj;
    }




    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        return FileUtil.class.getResource("/").getPath();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

    public static File readUserHomeFile(String pathName) {
        return new File(System.getProperty("user.home") + File.separator + pathName);
    }
}
