package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootDemo1Application {

	/**
	 * 0.java需要一个运行环境  内存大小 线程的大小 空间设定.. JVM调优
	 * 1.main方法中的args 是jvm虚拟机传递的参数.
	 * @param args
	 */

	public static void main(String[] args) {
		//传递的是class类型,运行期通过反射机制实例化对象
		SpringApplication.run(SpringbootDemo1Application.class, args);
	}

}
