package conf;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GetBeanTest {


    public static void main(String[] args) {
        // 通过Java配置来实例化Spring容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

        // 在Spring容器中获取Bean对象
        User user = context.getBean(User.class);
        System.out.println(user.toString());

        // 通过Java配置来实例化Spring容器

        // 在Spring容器中获取Bean对象
        Customer customer = context.getBean(Customer.class);
        System.out.println(customer.toString());

        // 销毁该容器
        context.destroy();
    }
}
