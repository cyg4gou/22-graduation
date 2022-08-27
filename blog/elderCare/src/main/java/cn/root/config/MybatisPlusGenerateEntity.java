package cn.root.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MybatisPlusGenerateEntity {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/eldercaresystem?useUnicode=true&characterEncoding=UTF-8&serverTimeZone=UTC"
                        , "root"
                        , "root")
                .globalConfig(builder -> {
                    builder.author("cyg") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn") // 设置父包名
                            .moduleName("root") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.other, "D://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("view_count") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(
                        new FreemarkerTemplateEngine()
                ) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
