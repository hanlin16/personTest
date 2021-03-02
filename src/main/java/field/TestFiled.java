package field;

import field.vo.FiledOTO;
import field.vo.TenantFieldOTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestFiled {

    public static List<TenantFieldOTO> getInfo(Object oto) {
        List<TenantFieldOTO> list = new ArrayList<>();

        Field[] newFields = oto.getClass().getDeclaredFields();
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
                if(null == property){
                    System.out.println("**********");
                }
                String value = property.value();

                TenantFieldOTO tenantFieldOTO = new TenantFieldOTO(fieldName, value);
                list.add(tenantFieldOTO);

                tenantFieldOTO.setChild(getInfo(newField));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static void main(String[] args) {
        FiledOTO oto = new FiledOTO();
        List<TenantFieldOTO> list = getInfo(oto);
        System.out.println(list);
    }
}
