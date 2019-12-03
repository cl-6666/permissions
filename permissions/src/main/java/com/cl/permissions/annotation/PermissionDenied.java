package com.cl.permissions.annotation;

import com.cl.permissions.XPermissionActivity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目：permissions-Text
 * 版权：蒲公英公司 版权所有
 * 作者：Arry
 * 版本：1.0
 * 创建日期：2019-12-03
 * 描述：权限被拒绝 的注解
 * 修订历史：
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionDenied {

    int rquestCode() default XPermissionActivity.PARAM_REQUEST_CODE_DEFAULT;

}
