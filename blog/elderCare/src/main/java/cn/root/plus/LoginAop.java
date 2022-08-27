package cn.root.plus;

import cn.root.common.ResponseCode;
import cn.root.common.ResponseResult;
import cn.root.common.error.requestHeader.IdNotExistException;
import cn.root.common.error.requestHeader.TokenNotExistException;
import cn.root.mapper.UserMapper;
import cn.root.util.HttpContextUtils;
import cn.root.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
@Slf4j
public class LoginAop {

    @Autowired
    private UserMapper userMapper;

    @Pointcut("@annotation(cn.root.plus.Logind)")
    public void pt() {
    }

    @Around(value = "pt()")
    public Object NeedLogin(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
//        log.info("调用的方法为------>" + method.getName());
        Logind annotation = method.getAnnotation(Logind.class);
        if (annotation.required()) {
//            log.info(">>>>>>>>>>>>>>>>>>>>>>需要登录<<<<<<<<<<<<<<<<<<<<<<<<");
            String token = HttpContextUtils.getHttpServletRequest().getHeader("access-token");
            String id = HttpContextUtils.getHttpServletRequest().getHeader("access-id");
            if(id == null){
                throw new IdNotExistException();
            }
            if(token == null){
                throw new TokenNotExistException();
            }
//            System.out.println("token------>" + token + "\n" + "id------>" + id);
            Map<String, Object> map = JWTUtils.checkToken(token
                    , userMapper.selectById(id).getSalt());
//            log.info("***************************");
            System.out.println();
            Set<Map.Entry<String, Object>> entries = map.entrySet();
//            for (Map.Entry<String, Object> a : entries) {
//                System.out.println(a);
//            }
//            log.info("***************************");
            if (entries.size() == 0) {
                return ResponseResult.fail(ResponseCode.NO_LOGIN);
            }
//            log.info(">>>>>>>>>>>>>>>>>>>>>>token验证成功<<<<<<<<<<<<<<<<<<<<<<<<");
        } else {
//            log.info(">>>>>>>>>>>>>>>>>>>>>>不需要登录<<<<<<<<<<<<<<<<<<<<<<<<");
        }
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return proceed;
    }


    /**
     * 已经弃用
     */
    //    @Around(value = "pt()")
    @Deprecated
    public void NeedLogin(){
        System.out.println("-----------------环绕通知-------------");
        //Signature signature = joinPoint.getSignature();
        /*ResponseResult cn.root.controller.UserController.viewOther(String,String)*/
        //System.out.println("signature------->" + signature);
        /*public cn.root.common.ResponseResult cn.root.controller.UserController.viewOther(java.lang.String,java.lang.String)*/
        //System.out.println("signature.toLongString------->" + signature.toLongString());
        /*UserController.viewOther(..)*/
        //System.out.println("signature.toShortString------->" + signature.toShortString());
        /*viewOther*/
        //System.out.println("signature.getName------->" + signature.getName());
        /*1*/
        //System.out.println("signature.getModifiers------->" + signature.getModifiers());
        /*class cn.root.controller.UserController*/
        //System.out.println("signature.getDeclaringType------->" + signature.getDeclaringType());
        /*class cn.root.controller.UserController*/
        //System.out.println("signature.getDeclaringTypeName------->" + signature.getDeclaringTypeName());

        //Object[] args = joinPoint.getArgs();

        //System.out.println("args0---------" + args[0]);
        //System.out.println("args1---------" + args[1]);


        /*class cn.root.controller.UserController*/
        //System.out.println("----------------->" + signature.getDeclaringType());
        /*cn.root.controller.UserController*/
        //System.out.println("----------------->" + signature.getDeclaringTypeName());
        //Method[] methods = signature.getDeclaringType().getMethods();
        //for (Method method1 : methods) {
        //    System.out.println(method1.getName());
        //    }
        /*MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();*/
        /*try {
            method = signature
                    .getDeclaringType()
                    .getMethod(signature.getName(), String.class, String.class, String.class);
        } catch (UndeclaredThrowableException e) {
            e.printStackTrace();
        }*/
        /*System.out.println("method------>" + method);
        String token = HttpContextUtils.getHttpServletRequest().getHeader("access-token");
        String id = HttpContextUtils.getHttpServletRequest().getHeader("access-id");
        System.out.println("token------>" + token + "\n" + "id------>" + id);
        Logind annotation = method.getAnnotation(Logind.class);*/
        try {

            /*Map<String, Object> map = JWTUtils.checkToken(token
                    , userMapper.selectById(id).getSalt());
            System.out.println("--------------------------------");
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry<String, Object> a : entries) {
                System.out.println(a);
            }*/

            /*assert map != null;
            if(annotation.required()){
                System.out.println("--------需要登录----------");
                if(!Arrays.asList(args).contains(map.get("userId"))){
                    System.out.println(">>>>>>>>>>>>却没有需要登录<<<<<<<<<<");
                    return ResponseResult.fail(ErrorCode.NO_LOGIN);
                }
            }else{
                System.out.println("--------不需要登录----------");
                if(Arrays.asList(args).contains(map.get("userId"))){
                    System.out.println(">>>>>>>>>>>>却登录了<<<<<<<<<<");
                    return ResponseResult.fail(ErrorCode.ACCOUNT_LOGIN);
                }
            }*/

        } catch (Exception e) {
            ResponseResult.fail(ResponseCode.LOGIN_NOT_CORRECT);
        }

//        try {
//            Object proceed = joinPoint.proceed();
//            return proceed;
//        } catch (Throwable throwable) {
//            return null;
//        }
    }
}
