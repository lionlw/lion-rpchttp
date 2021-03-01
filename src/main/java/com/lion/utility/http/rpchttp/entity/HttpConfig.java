package com.lion.utility.http.rpchttp.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * http配置注解
 * 
 * @author lion
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpConfig {
	/**
	 * 地址
	 * 
	 * @return 结果
	 */
	String url() default "";

	/**
	 * 请求方式
	 * 
	 * @return 结果
	 */
	int method() default 0;

	/**
	 * 目标编码
	 * 
	 * @return 结果
	 */
	String targetEncoding() default "";

	/**
	 * 链接超时秒数
	 * 
	 * @return 结果
	 */
	int connectTimeoutSecond() default 0;

	/**
	 * 读取超时秒数
	 * 
	 * @return 结果
	 */
	int readTimeoutSecond() default 0;
}
