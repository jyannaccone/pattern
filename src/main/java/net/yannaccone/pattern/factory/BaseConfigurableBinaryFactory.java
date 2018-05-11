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

import java.lang.annotation.Annotation;

/**
 * A base implementation of the ConfigurableBinaryFactory interface that uses
 * {@link net.yannaccone.pattern.reflector.Reflector Reflector} to scan for a
 * user specified implementation of a base type, creates a new instance of
 * that implementation, and configures the new instance using a provided
 * Configuration implementation.  The base type must implement the
 * Configurable interface.
 * <p>
 * The Reflector requires a base package name to serve as the root of the
 * package hierarchy that will be scanned.  If not provided, the Reflector
 * will use a default root package.
 */
public abstract class BaseConfigurableBinaryFactory<T1 extends Configurable<T4>,
    T2 extends Annotation, T3 extends Annotation, T4 extends Configuration>
    extends BaseBinaryFactory<T1, T2, T3> implements ConfigurableBinaryFactory<T1, T4> {

  /**
   * Constructs a new BaseConfigurableBinaryFactory that will search for
   * implementation classes within the default reflectorNamespace.
   * See {@link net.yannaccone.pattern.reflector.Reflector Reflector}
   * for more information regarding the default namespace.
   */
  public BaseConfigurableBinaryFactory() {
  }

  /**
   * Constructs a new BaseConfigurableBinaryFactory that will search for
   * implementation classes within the provided reflectorNamespace.
   * 
   * @param reflectorPackages A variable-length String array representation of
   *        the root packages of the hierarchies to scan for implementation
   *        classes
   */
  public BaseConfigurableBinaryFactory(String... reflectorPackages) {
    
    super(reflectorPackages);
  }

  /* (non-Javadoc)
   * @see net.yannaccone.pattern.factory.ConfigurableBinaryFactory#create(
   *   java.lang.String,
   *   java.lang.String,
   *   net.yannaccone.pattern.factory.Configuration)
   */
  @Override
  public T1 create(String type1, String type2, T4 configuration) throws FactoryException,
      ConfigurationException {

    // Use the create method from BaseBinaryFactory to create the unconfigured instance
    T1 instance = create(type1, type2);
      
    // Call the configure method of the Configurable instance, passing it the
    // configuration instance.  The instance will use the Configuration to
    // configure itself.
    instance.configure(configuration);
    
    return instance;
  }

}
