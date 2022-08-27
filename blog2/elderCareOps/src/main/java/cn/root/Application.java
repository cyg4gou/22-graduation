package cn.root;

import cn.root.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        SpringUtil.setApplicationContext(applicationContext);
    }
}
