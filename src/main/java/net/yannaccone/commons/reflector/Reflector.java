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
package net.yannaccone.commons.reflector;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 * A utility class that initializes and maintains a singleton instance of
 * Reflections.  This eliminates the need to rescan classes every time
 * Reflections needs to be used.
 * <p>
 * The Reflector requires a base package name to serve as the root of the
 * package hierarchy that will be scanned.  If not provided, the Reflector
 * will use a default root package.
 */
public final class Reflector {

  /*
   *
   */
  private static final String DEFAULT_REFLECTION_PACKAGE = "net.yannaccone";
  
  /*
   * The singleton instance of Reflections
   */
  private static Reflections reflections;

  /**
   * Find and return a collection of Classes that have the specified annotationType
   * 
   * @param annotationType The annotation type to obtain.
   * @return A Collection of Class objects that were annotated with
   *         annotationType
   */
  public static final Collection<Class<?>> resolveAnnotatedClass(
      Class<? extends Annotation> annotationType) {

    initialize();

    return reflections.getTypesAnnotatedWith(annotationType);
  }
 
  /**
   * A convenience method to safely get the annotation from an annotated Class.
   * It will throw an IllegalValueException if the specified annotatedClass is
   * not annotated with the specified annotationType.
   * 
   * @param <T> The type parameter for the type to be returned
   * @param annotatedClass The annotatedClass from which to obtain the
   *                       Annotation
   * @param annotationType The type of annotation to obtain from the
   *                       annotatedClass
   * @return the annotationType instance associated with the provided
   *         annotatedClass
   * @throws ReflectorException if the annotatedClass is not annotated with
   *         the specified annotationType.
   * 
   */
  public static final <T extends Annotation> T getAnnotation(Class<?> annotatedClass,
      Class<T> annotationType) throws ReflectorException {
      
    T annotation = annotatedClass.getAnnotation(annotationType);

		if (annotation==null) {
		            
		  throw new ReflectorException("annotatedClass is not annotated with type " +
		      annotationType.getName());
		}

		return annotation;
  }

  /**
   * Scan the default package hierarchy to build a cache of all the annotated
   * classes.  This method only needs to be called once, but is idempotent so
   * additional calls will have no impact.  The default root package is
   * "net.yannaccone".
   */
  public static synchronized final void initialize() {

      if (reflections!=null) {
          return;
      }

      reflections = new Reflections(new ConfigurationBuilder()
          .setUrls(ClasspathHelper.forPackage(DEFAULT_REFLECTION_PACKAGE))
          .setScanners(new TypeAnnotationsScanner()));
  }

  /**
   * Scan the specified package hierarchies to build a cache of all the
   * annotated classes.  This method only needs to be called once, but is
   * idempotent so additional calls will have no impact.
   * 
   * @param reflectorPackages a variable-length array of String
   *        representations of the root packages in the hierarchies that the
   *        Reflector will scan.
   */
  public static synchronized final void initialize(String... reflectorPackages) {

      if (reflections!=null) {
          return;
      }
      
      Set<URL> packageUrls = new HashSet<>();
      for (String reflectorPackage : reflectorPackages) {
        packageUrls.addAll(ClasspathHelper.forPackage(reflectorPackage));
      }

      reflections = new Reflections(new ConfigurationBuilder()
          .setUrls(packageUrls)
          .setScanners(new TypeAnnotationsScanner()));
  }

}
