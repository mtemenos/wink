/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *  
 *   http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *  
 *******************************************************************************/

package org.apache.wink.server.internal.registry;

import org.apache.wink.common.DynamicResource;
import org.apache.wink.common.internal.uritemplate.JaxRsUriTemplateProcessor;
import org.apache.wink.common.internal.uritemplate.UriTemplateProcessor;

/**
 * TODO: Document me!
 *
 * @author taubert
 *
 */
public class RawResource {
	private final Object instance;
	private final double priority;
	private final String uri;
	private UriTemplateProcessor processor = null;
	
	public RawResource(Object o, double priority){
		this.instance = o;
		this.priority = priority;
		String sUri = null;
		if (o instanceof DynamicResource){
			sUri = ((DynamicResource)o).getPath();
		}
		uri = sUri;
		if (sUri.startsWith("/")){
			sUri = sUri.substring(1);
		}
		if (!sUri.endsWith("/")){
			sUri += "/";
		}
		processor = JaxRsUriTemplateProcessor.newNormalizedInstance(sUri);
	}
	
	protected String getUri(){
		return this.uri;
	}
	
	protected UriTemplateProcessor getProcessor(){
		if (processor == null){
			String sUri = uri;
			if (sUri.startsWith("/")){
				sUri = sUri.substring(1);
			}
			if (!sUri.endsWith("/")){
				sUri += "/";
			}
			processor = JaxRsUriTemplateProcessor.newNormalizedInstance(sUri);
		}
		return processor;
	}
	
	protected Object getInstance(){
		return this.instance;
	}
	
	protected double getPriority(){
		return this.priority;
	}

}
