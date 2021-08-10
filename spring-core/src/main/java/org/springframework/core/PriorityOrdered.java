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

package org.springframework.core;

/**
 * Extension of the {@link Ordered} interface, expressing a <em>priority</em>
 * ordering: {@code PriorityOrdered} objects are always applied before
 * <em>plain</em> {@link Ordered} objects regardless of their order values.
 * Ordered接口的扩展,表达优先级排序: PriorityOrdered对象总是在普通Ordered对象之前应用,无论他们的顺序值如何》
 *
 * <p>When sorting a set of {@code Ordered} objects, {@code PriorityOrdered}
 * objects and <em>plain</em> {@code Ordered} objects are effectively treated as
 * two separate subsets, with the set of {@code PriorityOrdered} objects preceding
 * the set of <em>plain</em> {@code Ordered} objects and with relative
 * ordering applied within those subsets.
 * 在一组Ordered对象进行排序时,PriorityOrdered对象和普通Ordered对象被有效的视为两个独立的子集,
 * 其中PriorityOrdered的集合位于普通Ordered对象之前,并在这些子集中应用相应顺序.
 *
 * <p>This is primarily a special-purpose interface, used within the framework
 * itself for objects where it is particularly important to recognize
 * <em>prioritized</em> objects first, potentially without even obtaining the
 * remaining objects. A typical example: prioritized post-processors in a Spring
 * {@link org.springframework.context.ApplicationContext}.
 * 这主要是一个特殊用途的接口,在框架本身内用于对象,在这些对象中,首先识别出优先对象特别重要,甚至可能不需要获取剩下的对象.
 * 一个典型的例子,优先级的后置处理器在Spring应用上下文中
 *
 * <p>Note: {@code PriorityOrdered} post-processor beans are initialized in
 * a special phase, ahead of other post-processor beans. This subtly
 * affects their autowiring behavior: they will only be autowired against
 * beans which do not require eager initialization for type matching.
 * 请注意:PriorityOrdered后置处理器 bean在一个特殊的阶段初始化,在其他后置处理器bean之前.
 * 这会微妙的影响他们自动装配的行为: 他们只会针对不需要预先初始化进行类型匹配的bean自动装配.
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 2.5
 * @see org.springframework.beans.factory.config.PropertyOverrideConfigurer
 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
 */
public interface PriorityOrdered extends Ordered {
}
