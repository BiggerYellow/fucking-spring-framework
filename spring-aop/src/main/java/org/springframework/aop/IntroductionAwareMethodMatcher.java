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

package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * A specialized type of {@link MethodMatcher} that takes into account introductions
 * when matching methods. If there are no introductions on the target class,
 * a method matcher may be able to optimize matching more effectively for example.
 * MethodMatcher的特殊类型,当匹配方法时考虑introductions.
 * 如果在目标类中没有introduction,方法匹配器可能能够更有效率的优化匹配
 *
 * @author Adrian Colyer
 * @since 2.0
 */
public interface IntroductionAwareMethodMatcher extends MethodMatcher {

	/**
	 * Perform static checking whether the given method matches. This may be invoked
	 * instead of the 2-arg {@link #matches(java.lang.reflect.Method, Class)} method
	 * if the caller supports the extended IntroductionAwareMethodMatcher interface.
	 * 执行静态检查 给定的方法是否匹配. 如果调用者指出继承IntroductionAwareMethodMatcher接口  可以调用他而不是调用2个参数接口
	 * @param method the candidate method
	 * @param targetClass the target class
	 * @param hasIntroductions {@code true} if the object on whose behalf we are
	 * asking is the subject on one or more introductions; {@code false} otherwise
	 *                                     true 代表我们所代表的对象是一个或多个介绍的主题 ;false则不然
	 * @return whether or not this method matches statically
	 */
	boolean matches(Method method, Class<?> targetClass, boolean hasIntroductions);

}
