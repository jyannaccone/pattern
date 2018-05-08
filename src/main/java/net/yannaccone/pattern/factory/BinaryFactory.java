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
 * A generic abstract factory interface that uses two type Strings to locate
 * and distinctly identify an implementation. The T1 parameter is the base type
 * that must be extended by implementation classes that are returned by the
 * create method.  The T2 and T3 parameters are the annotation types that are
 * used to mark implementation classes.
 * <p>
 * Factory implementation requires the following:
 * <ul>
 * <li>A base type must be created from which concrete implementation classes
 *     that will be created by the Factory implementation are assignable from.
 *     The base type must be configured as parameter T1 for concrete Factory
 *     implementations.
 * <li>A first Annotation must be created to annotate implementation classes
 *     with a String type1 value.  Annotation interfaces must have an
 *     attribute method named value() with no default.  The Annotation
 *     type must configured as parameter T2 for concrete Factory
 *     implementations.
 * <li>A second Annotation must be created to annotate implementation classes
 *     with a String type2 value.  Annotation interfaces must have an
 *     attribute method named value() with no default.  The Annotation
 *     type must configured as parameter T3 for concrete Factory
 *     implementations.
 * <li>Implementation classes must be annotated with an Annotation of type
 *     specified by class parameter T2 and an Annotation of type specified
 *     by class parameter T3.
 * </ul>
 */
public interface BinaryFactory<T1, T2 extends Annotation, T3 extends Annotation> {

  /**
   * Create a new subclass instance of the type T1
   * 
   * @param type1 A first String that can be used to locate a type
   *        implementation
   * @param type2 A second String that can be used to locate a type
   *        implementation
   * @return An instance of the requested type implementation
   * @throws FactoryException if the Factory is unable to create the instance
   */
  T1 create(String type1, String type2) throws FactoryException;

}
