package com.lion.utility.http.rpchttp.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将实体转换为post注解
 * 
 * @author lion
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestEntity {
	/**
	 * 是否必填（暂时没使用到）
	 * 
	 * @return 结果
	 */
	boolean required() default true;
}
