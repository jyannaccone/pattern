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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.yannaccone.pattern.factory.Configuration;

/**
 * A Configuration implementation that stores configuration parameters as a
 * key-value Map of Object(s).
 */
public class MapConfiguration implements Configuration {
  
  /*
   * 
   */
  private final Map<Object,Object> configurationMap;

  /**
   * 
   */
  public MapConfiguration() {
    
    this.configurationMap = new HashMap<>();
  }
  
  /**
   * Add a configuration property to the configurationMap.
   * 
   * @param key the Object to add as the key
   * @param value the Object to add as the value
   */
  public void add(Object key, Object value) {
    
    configurationMap.put(key, value);
  }

  /**
   * Get the keys from the configurationMap
   * 
   * @return a unmodifiable Set of key Object(s) from the configurationMap
   */
  public Set<Object> keySet() {
    
    return Collections.unmodifiableSet(configurationMap.keySet());
  }
  
  /**
   * Get the entries from the configurationMap
   * 
   * @return an immutable Set of Entry(s) from the configurationMap
   */
  public Set<Map.Entry<Object,Object>> entrySet() {
    
    return Collections.unmodifiableSet(configurationMap.entrySet());
  }
  
}
