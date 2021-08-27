/*
 * Copyright 2002-2018 the original author or authors.
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
 * Enables support for handling components marked with AspectJ's {@code @Aspect} annotation,
 * similar to functionality found in Spring's {@code <aop:aspectj-autoproxy>} XML element.
 * 使能支持处理使用@Aspect标记的组件,功能同在Spring xml中的 <aop:aspectj-autoproxy/>
 * To be used on @{@link Configuration} classes as follows:
 * 使用在如下所示的配置类中
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableAspectJAutoProxy
 * public class AppConfig {
 *
 *     &#064;Bean
 *     public FooService fooService() {
 *         return new FooService();
 *     }
 *
 *     &#064;Bean
 *     public MyAspect myAspect() {
 *         return new MyAspect();
 *     }
 * }</pre>
 *
 * Where {@code FooService} is a typical POJO component and {@code MyAspect} is an
 * {@code @Aspect}-style aspect:
 * FooService是一个典型的pojo组件 且 MyAspect是一个@Aspect风格的切面
 *
 * <pre class="code">
 * public class FooService {
 *
 *     // various methods
 * }</pre>
 *
 * <pre class="code">
 * &#064;Aspect
 * public class MyAspect {
 *
 *     &#064;Before("execution(* FooService+.*(..))")
 *     public void advice() {
 *         // advise FooService methods as appropriate
 *     }
 * }</pre>
 *
 * In the scenario above, {@code @EnableAspectJAutoProxy} ensures that {@code MyAspect}
 * will be properly processed and that {@code FooService} will be proxied mixing in the
 * advice that it contributes.
 * 在上述场景下,@EnableAspectJAutoProxy保证MyAspect将被正确处理,并且FooService将被混合在他提供的切面中
 *
 * <p>Users can control the type of proxy that gets created for {@code FooService} using
 * the {@link #proxyTargetClass()} attribute. The following enables CGLIB-style 'subclass'
 * proxies as opposed to the default interface-based JDK proxy approach.
 * 用户可以使用proxyTargetClass属性控制为FooService创建的代理模型.以下启用了CGLIB风格的子类代理,而不是基于默认接口的JDK代理方法.
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableAspectJAutoProxy(proxyTargetClass=true)
 * public class AppConfig {
 *     // ...
 * }</pre>
 *
 * <p>Note that {@code @Aspect} beans may be component-scanned like any other.
 * Simply mark the aspect with both {@code @Aspect} and {@code @Component}:
 * 表明@Aspect bean应该和其他bean一样被组件扫描.
 * 同时使用@Aspect和@Component来标记切面
 *
 * <pre class="code">
 * package com.foo;
 *
 * &#064;Component
 * public class FooService { ... }
 *
 * &#064;Aspect
 * &#064;Component
 * public class MyAspect { ... }</pre>
 *
 * Then use the @{@link ComponentScan} annotation to pick both up:
 * 然后使用ComponentScan注解来选择两者
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;ComponentScan("com.foo")
 * &#064;EnableAspectJAutoProxy
 * public class AppConfig {
 *
 *     // no explicit &#064Bean definitions required
 * }</pre>
 *
 * <b>Note: {@code @EnableAspectJAutoProxy} applies to its local application context only,
 * allowing for selective proxying of beans at different levels.</b> Please redeclare
 * {@code @EnableAspectJAutoProxy} in each individual context, e.g. the common root web
 * application context and any separate {@code DispatcherServlet} application contexts,
 * if you need to apply its behavior at multiple levels.
 * 请注意:@EnableAspectJAutoProxy 只适用于其本地的应用上下文,允许在不同级别对bean进行选择性代理.
 * 请重新声明@EnableAspectJAutoProxy在每个独立的上下文中, 例如 通用web应用上下文 和 任何单独的DispatcherServler应用上下文, 如果你需要在多个应用级别应用其行为
 *
 * <p>This feature requires the presence of {@code aspectjweaver} on the classpath.
 * While that dependency is optional for {@code spring-aop} in general, it is required
 * for {@code @EnableAspectJAutoProxy} and its underlying facilities.
 * 此功能需要 aspectjweaner 的类路径的存在.
 * 虽然该依赖项对于spring-aop是可选的,但对于@EnableAspectJAutoProxu及其底层设施是必须的
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @since 3.1
 * @see org.aspectj.lang.annotation.Aspect
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//注册AnnotationAwareAspectJAutoProxyCreator的bean定义
@Import(AspectJAutoProxyRegistrar.class)
public @interface EnableAspectJAutoProxy {

	/**
	 * Indicate whether subclass-based (CGLIB) proxies are to be created as opposed
	 * to standard Java interface-based proxies. The default is {@code false}.
	 * 表明是否要创建基于子类CGLIB的代理,而不是基于标准java接口的代理. 默认为false
	 */
	boolean proxyTargetClass() default false;

	/**
	 * Indicate that the proxy should be exposed by the AOP framework as a {@code ThreadLocal}
	 * for retrieval via the {@link org.springframework.aop.framework.AopContext} class.
	 * Off by default, i.e. no guarantees that {@code AopContext} access will work.
	 * 表明代理应由AOP框架公开为ThreadLocal以通过AopContext类进行检索.
	 * 默认为关闭, 例如不保证AopContext访问会起作用
	 * @since 4.3.1
	 */
	boolean exposeProxy() default false;

}
