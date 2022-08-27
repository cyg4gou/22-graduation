package cn.root.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig{

    private final String baseUrl;

    public SwaggerConfig(
            @Value("${springfox.documentation.swagger-ui.base-url:}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
     */
    @Value("${swagger.opened}")
    private Boolean enable;

    /**
     * 项目应用名
     */
    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 项目版本信息
     */
    @Value("${spring.application.version}")
    private String applicationVersion;

    /**
     * 项目描述信息
     */
    @Value("${spring.application.description}")
    private String applicationDescription;

    @Bean
    Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                //将servlet路径映射（如果有）添加到apis基本路径
                .pathMapping("/")
                // 定义是否开启swagger，false为关闭，可以通过变量控制
                .enable(enable)
                .select()
                //配置需要扫描的controller位置
                .apis(RequestHandlerSelectors
                        .basePackage("cn.root.controller"))
                //配置路径
                .paths(PathSelectors.any())
                //构建
                .build()
                //文档信息
                .apiInfo(new ApiInfoBuilder()
                        //标题
                        .title(applicationName + " RESTFul API 文档")
                        //版本
                        .version("Application Version: " + applicationVersion + ", " +
                                "Spring Boot Version: " + SpringBootVersion.getVersion())
                        //描述
                        .description(applicationDescription)
                        //联系人信息
                        .contact(new Contact("cyg",//名字
                                "https://www.baidu.com/",//网址
                                "emailOfCyg"))//邮箱
                        //License
                        .license("Apache 2.0")
                        //License 网址
                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                        .build())

                // 支持的通讯协议集合
                .protocols(new LinkedHashSet<>(
                        Arrays.asList("https", "http")))

                // 授权信息设置，必要的header token等认证信息
                .securitySchemes(Collections.singletonList(
                        new ApiKey("BASE_TOKEN", "token", "pass")))

                // 授权信息全局应用
                .securityContexts(Collections.singletonList(
                                SecurityContext
                                        .builder()
                                        .securityReferences(Collections
                                                .singletonList(
                                                        new SecurityReference("BASE_TOKEN",
                                                                new AuthorizationScope[] {
                                                                        new AuthorizationScope("global", "")
                                                                }
                                                        )
                                                )
                                        )
                                        .build()
                        )
                );
    }
}