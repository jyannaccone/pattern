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
 * A base implementation of the ConfigurableFactory interface that uses
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
public abstract class BaseConfigurableFactory<T1 extends Configurable<T3>,
    T2 extends Annotation, T3 extends Configuration>
    extends BaseFactory<T1, T2> implements ConfigurableFactory<T1, T3> {

  /**
   * Constructs a new BaseConfigurableFactory that will search for
   * implementation classes within the default reflectorNamespace.
   * See {@link net.yannaccone.pattern.reflector.Reflector Reflector}
   * for more information regarding the default namespace.
   */
  public BaseConfigurableFactory() {
  }

  /**
   * Constructs a new BaseConfigurableFactory that will search for
   * implementation classes within the provided reflectorNamespace.
   * 
   * @param reflectorPackages A variable-length String array representation of
   *        the root packages of the hierarchies to scan for implementation
   *        classes
   */
  public BaseConfigurableFactory(String... reflectorPackages) {
    
    super(reflectorPackages);
  }

  /* (non-Javadoc)
   * @see net.yannaccone.pattern.factory.ConfigurableFactory#create(java.lang.String, net.yannaccone.pattern.factory.Configuration)
   */
  @Override
  public T1 create(String type, T3 configuration) throws FactoryException,
      ConfigurationException {

    // Use the create method from BaseFactory to create the unconfigured instance
    T1 instance = create(type);
      
    // Call the configure method of the Configurable instance, passing it the
    // configuration instance.  The instance will use the Configuration to
    // configure itself.
    instance.configure(configuration);
    
    return instance;
  }

}
