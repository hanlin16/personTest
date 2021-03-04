package reflect;

import reflect.vo.Customer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestReflect {

    public static void getObjectValue(Object object) throws Exception {
        Class<?> clz = object.getClass();
        Field[] fields = clz.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
            Method m = object.getClass().getMethod("get" + getMethodName(field.getName()));
            Object val = m.invoke(object);
            if (null != val) {
                System.out.println("String " + fieldName + ":" + val.toString());
            }


        }

    }

    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String getMethodName(String filedName) {
        byte[] items = filedName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    public static void main(String[] args) throws Exception {
        Customer customer = new Customer("张三", "18845222222");
        getObjectValue(customer);
    }
}
