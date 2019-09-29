package conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "conf")
public class BeanConfig {

    @Bean
    public User getUser(){
        return new User();
    }
}
