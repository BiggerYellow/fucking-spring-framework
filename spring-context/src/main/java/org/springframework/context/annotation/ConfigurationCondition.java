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

/**
 * A {@link Condition} that offers more fine-grained control when used with
 * {@code @Configuration}. Allows certain {@link Condition Conditions} to adapt when they match
 * based on the configuration phase. For example, a condition that checks if a bean
 * has already been registered might choose to only be evaluated during the
 * {@link ConfigurationPhase#REGISTER_BEAN REGISTER_BEAN} {@link ConfigurationPhase}.
 *
 * @author Phillip Webb
 * @since 4.0
 * @see Configuration
 */
public interface ConfigurationCondition extends Condition {

	/**
	 * Return the {@link ConfigurationPhase} in which the condition should be evaluated.
	 */
	ConfigurationPhase getConfigurationPhase();


	/**
	 * The various configuration phases where the condition could be evaluated.
	 * 可以评估条件的各种配置阶段
	 */
	enum ConfigurationPhase {

		/**
		 * The {@link Condition} should be evaluated as a {@code @Configuration}
		 * class is being parsed.
		 * Condition 应该被评估为@Configuration类正在被解析
		 * <p>If the condition does not match at this point, the {@code @Configuration}
		 * class will not be added.
		 * 如果此时条件不匹配,@Configuration配置类将不会被添加
		 */
		PARSE_CONFIGURATION,

		/**
		 * The {@link Condition} should be evaluated when adding a regular
		 * (non {@code @Configuration}) bean. The condition will not prevent
		 * {@code @Configuration} classes from being added.
		 * 在添加一个常规(非@Configuration)的bean时应该被评估为Condition.这个条件不会阻止@Configuration的类被添加
		 * <p>At the time that the condition is evaluated, all {@code @Configuration}s
		 * will have been parsed.
		 * 在评估条件时,所有@Configuration类将被解析
		 */
		REGISTER_BEAN
	}

}
