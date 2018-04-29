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

import net.yannaccone.commons.exception.CommonsException;

/**
 * An exception indicating that a requested item was not found.
 */
public class ReflectorException extends CommonsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ReflectorException() {
	}

	/**
	 * @param message A message explaining the exception
	 */
	public ReflectorException(String message) {
		super(message);
	}

	/**
	 * @param cause The Throwable that caused the exception
	 */
	public ReflectorException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message A message explaining the exception
	 * @param cause The Throwable that caused the exception
	 */
	public ReflectorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message A message explaining the exception
	 * @param cause The Throwable that caused the exception
	 * @param enableSuppression A boolean indicating whether or not suppression
	 *        is enabled or disabled
	 * @param writableStackTrace A boolean indicating whether or not the stack
	 *        trace should be writable
	 */
	public ReflectorException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
