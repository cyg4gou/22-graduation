package cn.root.plus;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logind {

    /**
     * 是否需要登录，默认需要
     * @return boolean
     */
    boolean required() default true;
}
