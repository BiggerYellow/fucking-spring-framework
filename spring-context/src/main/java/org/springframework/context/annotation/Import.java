/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates one or more <em>component classes</em> to import &mdash; typically
 * {@link Configuration @Configuration} classes.
 * 表明一个或多个要导入的配置类，典型的是@Configuration修饰的类
 * <p>Provides functionality equivalent to the {@code <import/>} element in Spring XML.
 * Allows for importing {@code @Configuration} classes, {@link ImportSelector} and
 * {@link ImportBeanDefinitionRegistrar} implementations, as well as regular component
 * classes (as of 4.2; analogous to {@link AnnotationConfigApplicationContext#register}).
 * 在Spring XML中import元素提供了相等的功能。允许导入@Configuration修饰的类、ImportSelector和ImportBeanDefinitionRegistrar实现类，以及常规组件类

 * <p>{@code @Bean} definitions declared in imported {@code @Configuration} classes should be
 * accessed by using {@link org.springframework.beans.factory.annotation.Autowired @Autowired}
 * injection. Either the bean itself can be autowired, or the configuration class instance
 * declaring the bean can be autowired. The latter approach allows for explicit, IDE-friendly
 * navigation between {@code @Configuration} class methods.
 * 在导入的@Configuration类中声明的bean定义应该通过使用@Autowired注入。bean本身或配置类实例都可以声明bean可以被注入。
 * 后一种方法允许显示的、ide友好的在@Configuration类方法之间导航
 * <p>May be declared at the class level or as a meta-annotation.
 * 可以在类级别声明或当做元注解

 * <p>If XML or other non-{@code @Configuration} bean definition resources need to be
 * imported, use the {@link ImportResource @ImportResource} annotation instead.
 * 如果需要导入XML或其他非@Configuration的类定义资源，使用@ImportResource注解代替

 * @author Chris Beams
 * @author Juergen Hoeller
 * @since 3.0
 * @see Configuration
 * @see ImportSelector
 * @see ImportBeanDefinitionRegistrar
 * @see ImportResource
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Import {

	/**
	 * {@link Configuration @Configuration}, {@link ImportSelector},
	 * {@link ImportBeanDefinitionRegistrar}, or regular component classes to import.
	 */
	Class<?>[] value();

}
