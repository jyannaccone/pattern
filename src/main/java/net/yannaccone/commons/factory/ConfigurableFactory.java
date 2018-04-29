/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership.  The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package net.yannaccone.commons.factory;

/**
 * An interface that provided functionality that allows instances created by
 * a Factory implementation to be automatically configured after instantiation.
 */
public interface ConfigurableFactory<T1 extends Configurable<T2>, T2 extends Configuration> {

  /**
   * Create a new subclass instance of the type T1
   * 
   * @param type A String that can be used to locate a type implementation
   * @param configuration A Configuration implementation that can be used
   *        to configure the created instance
   * @return An instance of the requested type implementation
   * @throws FactoryException if the Factory is unable to create the instance
   * @throws ConfigurationException if the Factory is unable to configure the
   *         created instance
   */
  T1 create(String type, T2 configuration) throws FactoryException, ConfigurationException;
  
}
