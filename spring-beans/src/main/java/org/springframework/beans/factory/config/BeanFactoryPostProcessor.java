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

package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

/**
 * Factory hook that allows for custom modification of an application context's
 * bean definitions, adapting the bean property values of the context's underlying
 * bean factory.
 * 工厂钩子，允许自定义应用上下文bean定义的修改，调整上下文底层bean工厂的bean属性值
 *
 * <p>Useful for custom config files targeted at system administrators that
 * override bean properties configured in the application context. See
 * {@link PropertyResourceConfigurer} and its concrete implementations for
 * out-of-the-box solutions that address such configuration needs.
 * 面向系统管理员的自定义配置文件很有用，这些文件覆盖了应用程序上下文中配置的属性值。
 * 见PropertyResourceConfigurer 和他针对满足这样的配置需求的开箱即用解决方案的具体实现。
 *
 * <p>A {@code BeanFactoryPostProcessor} may interact with and modify bean
 * definitions, but never bean instances. Doing so may cause premature bean
 * instantiation, violating the container and causing unintended side-effects.
 * If bean instance interaction is required, consider implementing
 * {@link BeanPostProcessor} instead.
 * BeanFactoryPostProcessor可以与bean定义交互和修改，但绝不可能与bean实例交互。
 * 这样做有可能导致bean过早实例化，违反容器并导致意想不到的效果。
 * 如果需要bean实例交互，考虑实现BeanPostProcessor代替。
 *
 * <h3>Registration</h3>
 * 注册
 * <p>An {@code ApplicationContext} auto-detects {@code BeanFactoryPostProcessor}
 * beans in its bean definitions and applies them before any other beans get created.
 * A {@code BeanFactoryPostProcessor} may also be registered programmatically
 * with a {@code ConfigurableApplicationContext}.
 * 一个应用上下文自动检测 BeanFactoryPostProcessor bean在他的bean定义中，且在其他bean创建之前应用他们。
 * 一个BeanFactoryPostProcessor可以通过ConfigurableApplicationContext 以编程方式注册
 *
 * <h3>Ordering</h3>
 * 排序
 * <p>{@code BeanFactoryPostProcessor} beans that are autodetected in an
 * {@code ApplicationContext} will be ordered according to
 * {@link org.springframework.core.PriorityOrdered} and
 * {@link org.springframework.core.Ordered} semantics. In contrast,
 * {@code BeanFactoryPostProcessor} beans that are registered programmatically
 * with a {@code ConfigurableApplicationContext} will be applied in the order of
 * registration; any ordering semantics expressed through implementing the
 * {@code PriorityOrdered} or {@code Ordered} interface will be ignored for
 * programmatically registered post-processors. Furthermore, the
 * {@link org.springframework.core.annotation.Order @Order} annotation is not
 * taken into account for {@code BeanFactoryPostProcessor} beans.
 *  在上下文中自动检测的BeanFactoryPostProcessor bean将通过PriorityOrdered和Ordered语句排序。
 *  相反，通过ConfigurableApplicationContext 以编程方式注册的BeanFactoryPostProcessor的bean 将按注册顺序应用。
 *  任何通过实现PriorityOrdered或Ordered接口的排序语义将被编程式注册的后置处理器忽略。
 *  此外，BeanFactoryPostProcessor的bean不考虑@Order注解
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 06.07.2003
 * @see BeanPostProcessor
 * @see PropertyResourceConfigurer
 */
@FunctionalInterface
public interface BeanFactoryPostProcessor {

	/**
	 * Modify the application context's internal bean factory after its standard
	 * initialization. All bean definitions will have been loaded, but no beans
	 * will have been instantiated yet. This allows for overriding or adding
	 * properties even to eager-initializing beans.
	 * 在他标准实例化后，修改应用上下文内部bean工厂。所有的bean定义将被加载，但没有bean被实例化。
	 * 这甚至允许覆盖和添加属性，设置是急切初始化的bean
	 * @param beanFactory the bean factory used by the application context
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
