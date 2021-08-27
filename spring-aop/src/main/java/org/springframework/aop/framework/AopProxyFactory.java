/*
 * Copyright 2002-2012 the original author or authors.
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

package org.springframework.aop.framework;

/**
 * Interface to be implemented by factories that are able to create
 * AOP proxies based on {@link AdvisedSupport} configuration objects.
 *工厂实现的接口, 它可以基于AdvisedSupport配置对象创建AOP代理.
 *
 * <p>Proxies should observe the following contract:
 * 代理应该遵循以下规则
 * <ul>
 * <li>They should implement all interfaces that the configuration
 * indicates should be proxied.
 * 他们应该实现所有配置表明应该代理的接口
 * <li>They should implement the {@link Advised} interface.
 * 他们应该实现 Advised接口
 * <li>They should implement the equals method to compare proxied
 * interfaces, advice, and target.
 * 他们应该实现equals方法来比较代理接口、通知和目标
 * <li>They should be serializable if all advisors and target
 * are serializable.
 * 如果所有通知者和目标都是可序列化的,他们也应该是可序列化的
 * <li>They should be thread-safe if advisors and target
 * are thread-safe.
 * 如果通知者和目标都是线程安全的,他们应该也是线程安全的
 * </ul>
 *
 * <p>Proxies may or may not allow advice changes to be made.
 * 代理可能也可能不允许更新通知.
 * If they do not permit advice changes (for example, because
 * the configuration was frozen) a proxy should throw an
 * {@link AopConfigException} on an attempted advice change.
 * 如果他们不允许通知改变(例如 因为配置被冻结) 一个代理应该抛出 AOPConfigException 尝试去更改通知
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 */
public interface AopProxyFactory {

	/**
	 * Create an {@link AopProxy} for the given AOP configuration.
	 * 根据给定的配置 创建一个AOP代理
	 * @param config the AOP configuration in the form of an
	 * AdvisedSupport object
	 * @return the corresponding AOP proxy
	 * @throws AopConfigException if the configuration is invalid
	 */
	AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException;

}
