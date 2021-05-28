package com.getClass;

import com.getClass.vo.A;
import com.getClass.vo2.X;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class TestRe {

    public static void main(String[] args) {
        A a = new A();
        String str = "as[0].b.c.d.list.[name]";

        String result = getValue(str, a);
        System.out.println(result);

        X x = new X();
        String str2 = "as[0].y.z.name";
        String result1 = getValue(str2, x);
        System.out.println(result1);

    }

    public static String getValue(String str, Object object) {
        int index = getIndex(str);
        String fileName = getFileName(str);
        String actFileName = fileName;
        boolean isArray = false;
        if (fileName.startsWith("[")) {
            isArray = true;
            actFileName = fileName.substring(1, fileName.length() - 1);
        }

        String[] middleArray = getMiddle(str);
        System.out.println(index);
        System.out.println(fileName);
        System.out.println(actFileName);
        System.out.println(isArray);
        if (null != middleArray && middleArray.length > 0) {
            for (String ss : middleArray) {
                System.out.println(ss);
            }
        }


        if (null == middleArray || middleArray.length == 0) {
            if (!isArray) {
                return String.valueOf(object);
            }
            return getArrayValue(actFileName, object);

        }

        for (int i = 0; i < middleArray.length; i++) {
            System.out.println(object.getClass());
            object = getSubObject(middleArray[i], object);
        }
        if (null == object) {
            return null;
        }

        if (!isArray) {
            return String.valueOf(getSubObject(actFileName, object));
        }

        return getArrayValue(actFileName, object);

    }

    private static String getArrayValue(String actFileName, Object object) {
        StringBuilder value = new StringBuilder();
        List<Object> list = (List<Object>) object;
        for (Object obj : list) {
            String name = String.valueOf(getSubObject(actFileName, obj));
            value.append(name).append(",");
        }
        return fixResult(value.toString());
    }


    public static String fixResult(String result) {
        if (StringUtils.isBlank(result)) {
            return result;
        }
        if (!result.endsWith(",")) {
            return result;
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }

    public static Object getSubObject(String filedName, Object object) {
        Object subObject = null;
        Field[] newFields = object.getClass().getDeclaredFields();
        try {
            for (Field newField : newFields) {
                //去除静态属性
                if (Modifier.isStatic(newField.getModifiers())) {
                    continue;
                }

                newField.setAccessible(true);
                //字段名称
                String fieldName = newField.getName();
                if (!filedName.equals(fieldName)) {
                    continue;
                }

                //获取属性值
                Method m = object.getClass().getMethod("get" + getMethodName(fieldName));
                subObject = m.invoke(object);
                break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subObject;
    }

    public static Integer getIndex(String str) {
        int begin = str.indexOf("[") + 1;
        int end = str.indexOf("]");
        String s = str.substring(begin, end);
        return Integer.parseInt(s);
    }

    public static String getFileName(String str) {
        int num = str.lastIndexOf(".") + 1;
        String filedName = str.substring(num);
        return filedName;

    }

    public static String[] getMiddle(String str) {
        int begin1 = str.indexOf("]") + 2;
        int end1 = str.lastIndexOf(".");
        String middle = str.substring(begin1, end1);
        if (StringUtils.isBlank(middle)) {
            return new String[0];
        }

        String[] array = middle.split("\\.");

        return array;
    }

    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String getMethodName(String filedName) {
        byte[] items = filedName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

}
