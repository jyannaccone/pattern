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

import java.lang.annotation.Annotation;
import java.util.Collection;

import net.yannaccone.pattern.reflector.Reflector;

/**
 * A base implementation of the Factory interface that uses
 * {@link net.yannaccone.pattern.reflector.Reflector Reflector} to scan for a
 * user specified implementation of a base type and creates a new instance of
 * that implementation.
 * <p>
 * The Reflector requires a base package name to serve as the root of the
 * package hierarchy that will be scanned.  If not provided, the Reflector
 * will use a default root package.
 */
public abstract class BaseFactory<T1, T2 extends Annotation>
    implements Factory<T1, T2> {
  
  /**
   * Constructs a new BaseFactory that will scan for implementation classes
   * within the default reflectorPackage. See
   * {@link net.yannaccone.pattern.reflector.Reflector Reflector} for more
   * information regarding the default reflectorPackage.
   */
  public BaseFactory() {
    
    // Initialize the Reflector using the default reflectorPackage.
    Reflector.initialize();
  }
  
  /**
   * Constructs a new BaseFactory that will scan for implementation classes
   * within the provided reflectorPackage.  This is the constructor that should
   * be used for most implementations.
   * 
   * @param reflectorPackages A variable-length String array representation of
   *        the root packages of the hierarchies to scan for implementation
   *        classes
   */
  public BaseFactory(String... reflectorPackages) {
    
    // Initialize the Reflector using the provided reflectorPackages.
    Reflector.initialize(reflectorPackages);
  }

  /* (non-Javadoc)
   * @see net.yannaccone.pattern.factory.Factory#create(java.lang.String)
   */
  @Override
  public T1 create(String type) throws FactoryException {

    if (type==null || type.isEmpty()) {
      
      throw new FactoryException("Parameter type cannot be null or empty");
    }
    
    Collection<Class<?>> typeAnnotateds = Reflector
        .resolveAnnotatedClass(annotationClass());
    
    for (Class<?> typeAnnotated : typeAnnotateds) {
      
      if (!baseClass().isAssignableFrom(typeAnnotated)) {
        
        throw new FactoryException("Class " + typeAnnotated.getClass().getName() +
            " is annotated with " + annotationClass().getName() +
            " but is not assignable from " + baseClass().getName());
      }
        
      try {
          
        T2 annotation = Reflector.getAnnotation(
            typeAnnotated, annotationClass());
        
        if (type.equals(annotationValue(annotation))) {
            
            return instantiateSubClass(typeAnnotated);
        }
          
      } catch (Exception e) {
          
        throw new FactoryException("Failed to instantiate implementation with type " +
            type, e);
      }
    }
    
    throw new FactoryException("Failed to find " + baseClass().getName() +
        " implementation of type " + type);
  }
  
  /**
   * A method that returns a Class T1 instance; where T1 is the base type
   * of the class to create configured as parameter T1 of the Factory
   * implementation.
   * 
   * @return a Class instance of the base of the class to create.
   */
  protected abstract Class<T1> baseClass();
  
  /**
   * A method that will create a new instance of the provided class.  This
   * method must be implemented from the final concrete Factory implementation
   * that has access to the constructor of the subclass (which the BaseFactory
   * class may not be permitted to access).
   * 
   * @param subClass the subclass to instantiate
   * @return a new instance of the subclass, cast to the base class
   * @throws IllegalAccessException if the class or its nullary constructor is
   *         not accessible
   * @throws InstantiationException if this Class represents an abstract class,
   *         an interface, an array class, a primitive type, or void; or if the
   *         class has no nullary constructor; or if the instantiation fails
   *         for some other reason.
   */
  protected abstract T1 instantiateSubClass(Class<?> subClass) throws IllegalAccessException,
      InstantiationException;

  /**
   * A method that returns a Class T2 instance; where T2 is the annotation
   * class configured as parameter T2 of the Factory implementation.
   * 
   * @return a Class instance of the annotation type that is used to mark the
   *         implementation of the class to create
   */
  protected abstract Class<T2> annotationClass();
  
  /**
   * A method that accepts an annotation instance and returns the result
   * of calling its value() method.  The value() method of the annotation
   * instance must return a String.
   * 
   * @param annotation the annotation to get the value from
   * @return the String value() from the provided annotation
   */
  protected abstract String annotationValue(T2 annotation);

}
