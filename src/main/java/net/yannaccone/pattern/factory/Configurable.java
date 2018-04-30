/**
 * Copyright 2018 - Yannaccone Holdings, LLC.
 * 
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
package net.yannaccone.pattern.factory;

/**
 * An interface that must be implemented by types that will be instantiated
 * using the ConfigurableFactory interface.
 */
public interface Configurable<T1 extends Configuration> {
  
  /**
   * Configure the instance using attributes contained within the configuration
   * parameter.
   * 
   * @param configuration The configuration parameter.
   * @throws ConfigurationException is the Configurable instance is unable to
   *         configure itself using the provided Configuration parameter.
   */
  void configure(T1 configuration) throws ConfigurationException;

}
