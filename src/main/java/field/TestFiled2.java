package field;

import com.alibaba.fastjson.JSON;
import field.vo.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestFiled2 {
    public static List<TotalFieldOTO2> getInfo(Class<?> clazz, String modelCode) {
        List<TotalFieldOTO2> list = new ArrayList<>();
        Field[] newFields = clazz.getDeclaredFields();
        try {
            for (Field newField : newFields) {
                //去除静态属性
                if (Modifier.isStatic(newField.getModifiers())) {
                    continue;
                }
                newField.setAccessible(true);
                //字段名
                String fieldName = newField.getName();

                ApiModelProperty property = newField.getAnnotation(ApiModelProperty.class);

                String value = property.value();

                TotalFieldOTO2 tenantFieldOTO = new TotalFieldOTO2(fieldName, modelCode);
                list.add(tenantFieldOTO);

                Class<?> fieldClass = newField.getType();

                if (!fieldClass.isAnnotationPresent(ApiModel.class)) {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static void main(String[] args) {
        List<TotalFieldOTO2> list = new ArrayList<>();
        list.addAll(getInfo(OrderOTO.class, "order"));
        list.addAll(getInfo(OrderKindOTO.class, "orderKind"));
        list.addAll(getInfo(OrderCarOTO.class, "car"));
        list.addAll(getInfo(OrderPolicyOTO.class, "orderPolicy"));
        list.addAll(getInfo(OrderBenefitOTO.class, "orderBenefit"));
        list.addAll(getInfo(OrderCarOTO.class, "customerCar"));
        System.out.println(list);
        String str = JSON.toJSONString(list);
        System.out.println(str);
    }
}
