package cn.root;

import cn.root.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.oas.annotations.EnableOpenApi;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@EnableOpenApi
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        SpringUtil.setApplicationContext(applicationContext);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        System.out.println(new Date());
    }
}
