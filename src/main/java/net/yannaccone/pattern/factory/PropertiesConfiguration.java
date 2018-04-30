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
package net.yannaccone.pattern.factory;

import java.util.Properties;

/**
 * A Properties subclass that implements Configuration so it can be used as the
 * configuration object for a Configurable implementation by
 * ConfigurableFactory.
 */
public class PropertiesConfiguration extends Properties implements Configuration {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Create a new instance of PropertiesConfiguration with no default
   * properties.
   */
  public PropertiesConfiguration() {
  }

  /**
   * Create a new instance of PropertiesConfiguration using the provided
   * defaults Properties as the default property values.
   * 
   * @param defaults the default Properties to set
   */
  public PropertiesConfiguration(Properties defaults) {
    super(defaults);
  }

}
