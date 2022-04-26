package com;

import com.hcc.config.IndexService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author :huangchunchen
 * @date :Created in 2022/4/25 23:21
 * @description:
 */
public class test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
		IndexService bean = configApplicationContext.getBean(IndexService.class);
		System.out.println(bean);
	}
}
