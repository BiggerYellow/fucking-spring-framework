/*
 * Copyright 2002-2014 the original author or authors.
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

package org.springframework.beans.factory;

/**
 * Callback interface triggered at the end of the singleton pre-instantiation phase
 * during {@link BeanFactory} bootstrap. This interface can be implemented by
 * singleton beans in order to perform some initialization after the regular
 * singleton instantiation algorithm, avoiding side effects with accidental early
 * initialization (e.g. from {@link ListableBeanFactory#getBeansOfType} calls).
 * In that sense, it is an alternative to {@link InitializingBean} which gets
 * triggered right at the end of a bean's local construction phase.
 * 在BeanFactory引导期间单例预实例化阶段结束时触发的回调接口.
 * 这个接口可以被单例bean实现 为了在常规单例初始化算法置换执行一些初始化,避免意外早期初始化的副作用.
 * 在这个意义上来说,他是InitializingBean的替换品,他在bean的本地构造结束时被触发
 *
 * <p>This callback variant is somewhat similar to
 * {@link org.springframework.context.event.ContextRefreshedEvent} but doesn't
 * require an implementation of {@link org.springframework.context.ApplicationListener},
 * with no need to filter context references across a context hierarchy etc.
 * It also implies a more minimal dependency on just the {@code beans} package
 * and is being honored by standalone {@link ListableBeanFactory} implementations,
 * not just in an {@link org.springframework.context.ApplicationContext} environment.
 * 这个回调变体类似于ContextRefreshedEvent但不需要实现ApplicationListener,也不需要跨上下文过滤上下文引用等级制度.
 * 它还意味着对beans包的依赖性更小,并且受到独立的BeanFactory实现的支持,而不是仅仅是在ApplicationContext环境中
 *
 * <p><b>NOTE:</b> If you intend to start/manage asynchronous tasks, preferably
 * implement {@link org.springframework.context.Lifecycle} instead which offers
 * a richer model for runtime management and allows for phased startup/shutdown.
 * 请注意:如果你打算启动或管理异步任务,最好实现Lifecycle代替他为运行时管理提供丰富的模型 并允许分阶段使用
 *
 * @author Juergen Hoeller
 * @since 4.1
 * @see org.springframework.beans.factory.config.ConfigurableListableBeanFactory#preInstantiateSingletons()
 */
public interface SmartInitializingSingleton {

	/**
	 * Invoked right at the end of the singleton pre-instantiation phase,
	 * with a guarantee that all regular singleton beans have been created
	 * already. {@link ListableBeanFactory#getBeansOfType} calls within
	 * this method won't trigger accidental side effects during bootstrap.
	 * <p><b>NOTE:</b> This callback won't be triggered for singleton beans
	 * lazily initialized on demand after {@link BeanFactory} bootstrap,
	 * and not for any other bean scope either. Carefully use it for beans
	 * with the intended bootstrap semantics only.
	 */
	void afterSingletonsInstantiated();

}
