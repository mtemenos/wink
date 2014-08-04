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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.wink.common.DynamicResource;
import org.apache.wink.common.internal.lifecycle.ObjectFactory;
import org.apache.wink.common.internal.registry.metadata.ClassMetadata;
import org.apache.wink.common.internal.registry.metadata.MethodMetadata;
import org.apache.wink.common.internal.uritemplate.JaxRsUriTemplateProcessor;
import org.apache.wink.common.internal.uritemplate.UriTemplateMatcher;
import org.apache.wink.common.internal.uritemplate.UriTemplateProcessor;

/**
 * Record class to hold all the information of a resource. This includes the
 * metadata of the resource class and the {@link ObjectFactory} of the resource
 */
public class ResourceRecord extends TemplatedRecord {

    private ClassMetadata           metadata;
    private ObjectFactory<?>        objectFactory;
    private List<SubResourceRecord> subResources;
    private double                  priority;

	private final ResourceRecordFactory factory;
	private final DynamicResource raw;
	private final boolean lazy;
	private final boolean isRoot;
    
    public ResourceRecord(ClassMetadata metadata,
                          ObjectFactory<?> objectFactory,
                          UriTemplateProcessor processor) {
        super(processor);
        this.metadata = metadata;
        this.objectFactory = objectFactory;
        this.subResources = new LinkedList<SubResourceRecord>();
        this.priority = -1;
        this.factory = null;
        this.raw = null;
        lazy = false;
        this.isRoot = true;
        build();
    }
    
   
    /*
     * Lazy loading 
     */
	public ResourceRecord(ResourceRecordFactory factory, Object o, boolean isRoot) {
		super(getTemplateProcessor(o));

		if (!(o instanceof DynamicResource)){
			throw new RuntimeException("Only Dynamic resources can apply for lazy loading.");
		}
		this.factory = factory;
		this.raw = (DynamicResource)o; // Will be loaded only if necessary.
		this.isRoot = isRoot;
		lazy = true;
	}
	
	/*
	 * Lazy loading only.
	 */
	private static final UriTemplateProcessor getTemplateProcessor(Object o){
		String sUri = ((DynamicResource)o).getPath();
		if (sUri.startsWith("/")){
			sUri = sUri.substring(1);
		}
		if (!sUri.endsWith("/")){
			sUri += "/";
		}
		return JaxRsUriTemplateProcessor.newNormalizedInstance(sUri);
	}

	public void ensureLoaded(){
		if (lazy && this.metadata == null){
	        Class<? extends Object> cls = this.raw.getClass();
	        ClassMetadata metadata = this.factory.createMetadata(cls);
	        this.metadata = this.factory.fixInstanceMetadata(metadata, this.raw);
	        super.setTemplateProcessor(this.factory.createUriTemplateProcessor(metadata));
	        this.objectFactory = this.factory.getLifecycleManagerRegistry().getObjectFactory(this.raw);
	        this.subResources = new LinkedList<SubResourceRecord>();
			build();
		}
	}
	
    public double getPriority() {
        return this.priority;
    }

    void setPriority(double priority) {
        this.priority = priority;
    }

    /**
     * Get the metadata of the resource class
     * 
     * @return {@link ClassMetadata} of the resource
     */
    public ClassMetadata getMetadata() {
    	ensureLoaded();
        return this.metadata;
    }

    
    
    /**
     * Get the {@link ObjectFactory} of the resource
     * 
     * @return {@link ObjectFactory} of the resource
     */
    public ObjectFactory<?> getObjectFactory() {
    	ensureLoaded();
        return this.objectFactory;
    }

    /**
     * build the sorted list (in descending order) of sub-resources (methods and
     * locators) of the resource
     */
    private void build() {
        collectSubResourceMethods();
        collectSubResourceLocators();
        // we use the reverse-order comparator because the sort method
        // will sort the elements in ascending order, but we want
        // them sorted in descending order
        Collections.sort(subResources, Collections.reverseOrder());
    }

    /**
     * create sub-resource records for all sub-resource methods
     */
    private void collectSubResourceMethods() {
        List<MethodMetadata> subResourceMethods = metadata.getSubResourceMethods();
        for (MethodMetadata meta : subResourceMethods) {
            SubResourceMethodRecord record = new SubResourceMethodRecord(meta);
            subResources.add(record);
        }
    }

    /**
     * create sub-resource records for all sub-resource locators
     */
    private void collectSubResourceLocators() {
        List<MethodMetadata> subResourceLocators = metadata.getSubResourceLocators();
        for (MethodMetadata meta : subResourceLocators) {
            SubResourceLocatorRecord record = new SubResourceLocatorRecord(meta);
            subResources.add(record);
        }
    }

    /**
     * Return whether the resource has any sub-resources defined
     * 
     * @return true if there is at least one sub-resource (method or locator)
     */
    public boolean hasSubResources() {
    	ensureLoaded();
        return (this.subResources.size() > 0);
    }

    /**
     * Get a sorted list (in descending order) of all the sub-resources (methods
     * and locators) that match the given uri
     * 
     * @param uri the uri to match
     * @return a sorted list (in descending order) of matching sub-resources
     */
    public List<SubResourceInstance> getMatchingSubResources(String uri) {
    	ensureLoaded();
        return getMatchingSubResources(uri, true, true);
    }

    public List<SubResourceInstance> getMatchingSubResourceMethods(String uri) {
    	ensureLoaded();
        return getMatchingSubResources(uri, true, false);
    }

    public List<SubResourceInstance> getMatchingSubResourceLocators(String uri) {
    	ensureLoaded();
        return getMatchingSubResources(uri, false, true);
    }

    public List<SubResourceRecord> getSubResourceRecords() {
    	ensureLoaded();
        return this.subResources;
    }

    public List<SubResourceInstance> getMatchingSubResources(String uri,
                                                             boolean method,
                                                             boolean locator) {
    	
    	ensureLoaded();
        List<SubResourceInstance> list = new LinkedList<SubResourceInstance>();
        // add records according to the request uri
        for (SubResourceRecord record : subResources) {
            UriTemplateMatcher matcher = record.getTemplateProcessor().matcher();
            // if the uri is a match to the uri template
            if (matcher.matches(uri)) {
                if (method && record instanceof SubResourceMethodRecord && matcher.isExactMatch()) {
                    list.add(new SubResourceInstance(record, matcher));
                }
                if (locator && record instanceof SubResourceLocatorRecord) {
                    list.add(new SubResourceInstance(record, matcher));
                }
            }
        }
        return list;
    }

    @Override
    public String toString() {
    	ensureLoaded();
        return String.format("Path: %s; ClassMetadata: %s", super.toString(), //$NON-NLS-1$
        		this.metadata);
    }

    @Override
    public int compareTo(TemplatedRecord other) {
        if (other != null && other instanceof ResourceRecord) {
            double ret = this.priority - ((ResourceRecord)other).priority;
            if (ret < 0) {
                return -1;
            }
            if (ret > 0) {
                return 1;
            }
        }
        return super.compareTo(other);
    }
    
}
