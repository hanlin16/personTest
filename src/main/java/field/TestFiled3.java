package field;

import field.vo.FiledValueOTO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestFiled3 {

    public static Map<String, Object> getObjectValue(Object object, Set<String> set) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Class<?> clz = object.getClass();
        Field[] fields = clz.getDeclaredFields();

        for (Field field : fields) {
            if (!set.contains(field.getName())) {
                continue;
            }
            Method m = object.getClass().getMethod("get" + getMethodName(field.getName()));
            Object val = m.invoke(object);
            map.put(field.getName(), val);

        }

        return map;
    }

    private FiledValueOTO getValue(Field field, Object object) throws Exception {
        //String
        if (field.getGenericType().toString().equals("class java.lang.String")) {
            Method m = object.getClass().getMethod("get" + getMethodName(field.getName()));
            String val = (String) m.invoke(object);
            return new FiledValueOTO(field.getName(), val);
        }

        //Integer
        if (field.getGenericType().toString().equals("class java.lang.Integer")) {
            Method m = object.getClass().getMethod("get" + getMethodName(field.getName()));
            Integer val = (Integer) m.invoke(object);
            return new FiledValueOTO(field.getName(), val);
        }

        //Double
        if (field.getGenericType().toString().equals("class java.lang.Double")) {
            Method m = object.getClass().getMethod("get" + getMethodName(field.getName()));
            Double val = (Double) m.invoke(object);
            return new FiledValueOTO(field.getName(), val);
        }

        //Long
        if (field.getGenericType().toString().equals("class java.lang.Long")) {
            Method m = object.getClass().getMethod("get" + getMethodName(field.getName()));
            Long val = (Long) m.invoke(object);
            return new FiledValueOTO(field.getName(), val);
        }

        //Boolean 是封装类
        if (field.getGenericType().toString().equals("class java.lang.Boolean")) {
            Method m = object.getClass().getMethod(field.getName());
            Boolean val = (Boolean) m.invoke(object);
            return new FiledValueOTO(field.getName(), val);
        }

        // 反射找不到getter的具体名
        if (field.getGenericType().toString().equals("boolean")) {
            Method m = object.getClass().getMethod(field.getName());
            Boolean val = (Boolean) m.invoke(object);
            if (val != null) {
                System.out.println("boolean type:" + val);
            }
        }
        //Date
        if (field.getGenericType().toString().equals("class java.util.Date")) {
            Method m = object.getClass().getMethod("get" + getMethodName(field.getName()));
            Date val = (Date) m.invoke(object);
            return new FiledValueOTO(field.getName(), val);
        }
        //Short
        if (field.getGenericType().toString().equals("class java.lang.Short")) {
            Method m = object.getClass().getMethod("get" + getMethodName(field.getName()));
            Short val = (Short) m.invoke(object);
            return new FiledValueOTO(field.getName(), val);
        }

        return null;
    }

    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String getMethodName(String filedName) {
        byte[] items = filedName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

}
