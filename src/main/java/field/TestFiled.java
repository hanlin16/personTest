package field;

import com.alibaba.fastjson.JSON;
import field.vo.FiledOTO;
import field.vo.TotalFieldOTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestFiled {

    public static List<TotalFieldOTO> getInfo(Class<?> clazz) {
        List<TotalFieldOTO> list = new ArrayList<>();
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

                TotalFieldOTO totalFieldOTO = new TotalFieldOTO(fieldName, value);
                list.add(totalFieldOTO);

                Class<?> fieldClass = newField.getType();

                if (!fieldClass.isAnnotationPresent(ApiModel.class)) {
                    continue;
                }
                totalFieldOTO.setChild(getInfo(fieldClass));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static void main(String[] args) {
        List<TotalFieldOTO> list = getInfo(FiledOTO.class);
        System.out.println(list);
        String str = JSON.toJSONString(list);
        System.out.println(str);
    }
}
