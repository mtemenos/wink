/*
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
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
 
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.09.15 at 01:17:47 PM IDT 
//


package org.apache.wink.common.model.opensearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import org.apache.wink.common.RestConstants;
import org.apache.wink.common.RestException;
import org.apache.wink.common.internal.model.NamespacePrefixMapperProvider;
import org.apache.wink.common.internal.utils.JAXBUtils;
import org.apache.wink.common.model.JAXBNamespacePrefixMapper;


/**
 * The "OpenSearchDescription" element per OpenSearch specification
 * 
 * <pre>
 * The &quot;OpenSearchDescription&quot; element
 * 
 * The root node of the OpenSearch description document.
 *     
 *       Parent: None 
 *       Requirements: The element must appear exactly once as the root node of the document. 
 *     
 *   Example:
 *     
 *    &lt;OpenSearchDescription xmlns=&quot;http://a9.com/-/spec/opensearch/1.1/&quot;&gt;
 *       &lt;!--- ... ---&gt;
 *    &lt;/OpenSearchDescription&gt;
 *     
 * o The &quot;ShortName&quot; element
 *     
 *     Contains a brief human-readable title that identifies this search engine.
 *     
 *         Parent: OpenSearchDescription 
 *         Restrictions: The value must contain 16 or fewer characters of plain text. The value must not contain HTML or other markup. 
 *         Requirements: This element must appear exactly once. 
 *     
 *     Example:
 *     
 *      &lt;ShortName&gt;Web Search&lt;/ShortName&gt;
 *     
 * o The &quot;Description&quot; element
 *     
 *     Contains a human-readable text description of the search engine.
 *     
 *         Parent: OpenSearchDescription 
 *         Restrictions: The value must contain 1024 or fewer characters of plain text. The value must not contain HTML or other markup. 
 *         Requirements: This element must appear exactly once. 
 *     
 *     Example:
 *     
 *      &lt;Description&gt;Use Example.com to search the Web.&lt;/Description&gt;
 *     
 * o The &quot;Url&quot; element
 *     
 *     Describes an interface by which a search client can make search requests of the search engine.
 *     
 *     OpenSearch provides support for both index-based and page-based search engines. By default, both the first search result and the first page of search results are numbered &quot;1&quot;. Search engines can use the &quot;indexOffset&quot; and &quot;pageOffset&quot; attributes to inform search clients of different starting values.
 *     
 *         Parent: OpenSearchDescription 
 *         Attributes:
 *     
 *             template - Contains the search URL template to be processed according to the OpenSearch URL template syntax.
 *     
 *                 Requirements: This attribute is required. 
 *     
 *             type - Contains the MIME type of the search result format.
 *     
 *                 Restrictions: The value must be a valid MIME type. 
 *                 Requirements: This attribute is required. 
 *     
 *             indexOffset - Contains the index number of the first search result.
 *     
 *                 Restrictions: The value must be an integer. 
 *                 Default: &quot;1&quot; 
 *                 Requirements: This attribute is optional. 
 *     
 *             pageOffset - Contains the page number of the first set of search results.
 *     
 *                 Restrictions: The value must be an integer. 
 *                 Default: &quot;1&quot;. 
 *                 Requirements: This attribute is optional. 
 *     
 *         Requirements: This element must appear one or more times. 
 *     
 *     
 *     Example of a Url element describing the interface used to retrieve search results over RSS:
 *     
 *      &lt;Url type=&quot;application/rss+xml&quot;
 *           template=&quot;http://example.com/?q={searchTerms}&amp;pw={startPage?}&quot; /&gt;
 *     
 *     Example of a Url element describing the interface used to retrieve 0-based search results over XHTML:
 *     
 *      &lt;Url type=&quot;application/xhtml+xml&quot;
 *           indexOffset=&quot;0&quot;
 *           template=&quot;http://example.com/search?q={searchTerms}&amp;start={startIndex?}&quot; /&gt;
 *     
 * o The &quot;Contact&quot; element
 *     
 *     Contains an email address at which the maintainer of the description document can be reached.
 *     
 *         Parent: OpenSearchDescription 
 *         Restrictions: The value must conform to the requirements of Section 3.4.1 &quot;Addr-spec specification&quot; in RFC 2822. 
 *         Requirements: This element may appear zero or one time. 
 *     
 *     Example:
 *     
 *      &lt;Contact&gt;admin@example.com&lt;/Contact&gt;
 *     
 * o The &quot;Tags&quot; element
 *     
 *     Contains a set of words that are used as keywords to identify and categorize this search content. Tags must be a single word and are delimited by the space character (' ').
 *     
 *         Parent: OpenSearchDescription 
 *         Restrictions: The value must contain 256 or fewer characters of plain text. The value must not contain HTML or other markup. 
 *         Requirements: This element may appear zero or one time. 
 *     
 *     Example:
 *     
 *      &lt;Tags&gt;example web&lt;/Tags&gt;
 *     
 * o The &quot;LongName&quot; element
 *     
 *     Contains an extended human-readable title that identifies this search engine.
 *     
 *     Search clients should use the value of the ShortName element if this element is not available.
 *     
 *         Parent: OpenSearchDescription 
 *         Restrictions: The value must contain 48 or fewer characters of plain text. The value must not contain HTML or other markup. 
 *         Requirements: This element may appear zero or one time. 
 *     
 *     Example:
 *     
 *      &lt;LongName&gt;Example.com Web Search&lt;/LongName&gt;
 *     
 * o The &quot;Image&quot; element
 *     
 *     Contains a URL that identifies the location of an image that can be used in association with this search content.
 *     
 *     Image sizes are offered as a hint to the search client. The search client will chose the most appropriate image for the available space and should give preference to those listed first in the OpenSearch description document. Square aspect ratios are recommended. When possible, search engines should offer a 16x16 image of type &quot;image/x-icon&quot; or &quot;image/vnd.microsoft.icon&quot; (the Microsoft ICON format) and a 64x64 image of type &quot;image/jpeg&quot; or &quot;image/png&quot;.
 *     
 *         Parent: OpenSearchDescription 
 *         Attributes:
 *     
 *             height � Contains the height, in pixels, of this image.
 *     
 *                 Restrictions: The value must be a non-negative integer. 
 *                 Requirements: This attribute is optional. 
 *     
 *             width � Contains the width, in pixels, of this image.
 *     
 *                 Restrictions: The value must be a non-negative integer. 
 *                 Requirements: This attribute is optional. 
 *     
 *             type � Contains the the MIME type of this image.
 *     
 *                 Restrictions: The value must be a valid MIME type. 
 *                 Requirements: This attribute is optional. 
 *     
 *         Restrictions: The value must be a URI. 
 *         Requirements: This element may appear zero, one, or more times. 
 *     
 *     Example:
 *     
 *      &lt;Image height=&quot;16&quot; width=&quot;16&quot; type=&quot;image/x-icon&quot;&gt;http://example.com/favicon.ico&lt;/Image&gt;
 *      
 *      &lt;Image height=&quot;64&quot; width=&quot;64&quot; type=&quot;image/png&quot;&gt;http://example.com/websearch.png&lt;/Image&gt;
 *     
 * o The &quot;Query&quot; element
 *     
 *     Defines a search query that can be performed by search clients. Please see the OpenSearch Query element specification for more information.
 *     
 *     OpenSearch description documents should include at least one Query element of role=&quot;example&quot; that is expected to return search results. Search clients may use this example query to validate that the search engine is working properly.
 *     
 *         Parent: OpenSearchDescription 
 *         Requirements: This element may appear zero or more times. 
 *     
 *     Example:
 *     
 *      &lt;Query role=&quot;example&quot; searchTerms=&quot;cat&quot; /&gt;
 *     
 * o The &quot;Developer&quot; element
 *     
 *     Contains the human-readable name or identifier of the creator or maintainer of the description document.
 *     
 *     The developer is the person or entity that created the description document, and may or may not be the owner, author, or copyright holder of the source of the content itself.
 *     
 *         Parent: OpenSearchDescription 
 *         Restrictions: The value must contain 64 or fewer characters of plain text. The value must not contain HTML or other markup. 
 *         Requirements: This element may appear zero or one time. 
 *     
 *     Example:
 *     
 *      &lt;Developer&gt;Example.com Development Team&lt;/Developer&gt;
 *     
 * o The &quot;Attribution&quot; element
 *     
 *     Contains a list of all sources or entities that should be credited for the content contained in the search feed.
 *     
 *         Parent: OpenSearchDescription 
 *         Restrictions: The value must contain 256 or fewer characters of plain text. The value must not contain HTML or other markup. 
 *         Requirements: This element may appear zero or one time. 
 *     
 *     Example:
 *     
 *      &lt;Attribution&gt;Search data copyright Example.com, Inc.&lt;/Attribution&gt;
 *     
 * o The &quot;SyndicationRight&quot; element
 *     
 *     Contains a value that indicates the degree to which the search results provided by this search engine can be queried, displayed, and redistributed.
 *     
 *         Parent: OpenSearchDescription 
 *         Values: The value must be one of the following strings (case insensitive):
 *     
 *             &quot;open&quot; �
 *     
 *                 The search client may request search results. 
 *                 The search client may display the search results to end users. 
 *                 The search client may send the search results to other search clients. 
 *     
 *             &quot;limited&quot; �
 *     
 *                 The search client may request search results. 
 *                 The search client may display the search results to end users. 
 *                 The search client may not send the search results to other search clients. 
 *     
 *             &quot;private&quot; �
 *     
 *                 The search client may request search results. 
 *                 The search client may not display the search results to end users. 
 *                 The search client may not send the search results to other search clients. 
 *     
 *             &quot;closed&quot; -
 *     
 *                 The search client may not request search results. 
 *     
 *         Default: &quot;open&quot; 
 *         Requirements: This element may appear zero or one time. 
 *     
 *     Example:
 *     
 *      &lt;SyndicationRight&gt;open&lt;/SyndicationRight&gt;
 *     
 * o The &quot;AdultContent&quot; element
 *     
 *     Contains a boolean value that should be set to true if the search results may contain material intended only for adults.
 *     
 *     As there are no universally applicable guidelines as to what constitutes &quot;adult&quot; content, the search engine should make a good faith effort to indicate when there is a possibility that search results may contain material inappropriate for all audiences.
 *     
 *         Parent: OpenSearchDescription 
 *         Values:
 *     
 *             The values &quot;false&quot;, &quot;FALSE&quot;, &quot;0&quot;, &quot;no&quot;, and &quot;NO&quot; will be considered boolean FALSE; all other strings will be considered boolean TRUE. 
 *     
 *         Default: &quot;false&quot; 
 *         Requirements: This element may appear zero or one time. 
 *     
 *     Example:
 *     
 *      &lt;AdultContent&gt;false&lt;/AdultContent&gt;
 *     
 * o The &quot;Language&quot; element
 *     
 *     Contains a string that indicates that the search engine supports search results in the specified language.
 *     
 *     An OpenSearch description document should include one &quot;Language&quot; element for each language that the search engine supports. If the search engine also supports queries for any arbitrary language then the OpenSearch description document should include a Language element with a value of &quot;*&quot;. The &quot;language&quot; template parameter in the OpenSearch URL template can be used to allow the search client to choose among the available languages.
 *     
 *         Parent: OpenSearchDescription 
 *         Restrictions: The value must conform to the XML 1.0 Language Identification, as specified by RFC 3066. In addition, the value of &quot;*&quot; will signify that the search engine does not restrict search results to any particular language. 
 *         Default: &quot;*&quot;. 
 *         Requirements: This element may appear zero, one, or more times. 
 *     
 *     Example:
 *     
 *      &lt;Language&gt;en-us&lt;/Language&gt;
 *      
 *      &lt;Language&gt;*&lt;/Language&gt;
 *     
 * o The &quot;InputEncoding&quot; element
 *     
 *     Contains a string that indicates that the search engine supports search requests encoded with the specified character encoding.
 *     
 *     An OpenSearch description document should include one &quot;InputEncoding&quot; element for each character encoding that can be used to encode search requests. The &quot;inputEncoding&quot; template parameter in the OpenSearch URL template can be used to require the search client to identify which encoding is being used to encode the current search request.
 *     
 *         Parent: OpenSearchDescription 
 *         Restrictions: The value must conform to the XML 1.0 Character Encodings, as specified by the IANA Character Set Assignments. 
 *         Default: &quot;UTF-8&quot;. 
 *         Requirements: This element may appear zero, one, or more times. 
 *     
 *     Example:
 *     
 *      &lt;InputEncoding&gt;UTF-8&lt;/InputEncoding&gt;
 *     
 * o The &quot;OutputEncoding&quot; element
 *     
 *     Contains a string that indicates that the search engine supports search responses encoded with the specified character encoding.
 *     
 *     An OpenSearch description document should include one &quot;OutputEncoding&quot; element for each character encoding that can be used to encode search responses. The &quot;outputEncoding&quot; template parameter in the OpenSearch URL template can be used to allow the search client to choose a character encoding in the search response.
 *     
 *         Parent: OpenSearchDescription 
 *         Restrictions: The value must conform to the XML 1.0 Character Encodings, as specified by the IANA Character Set Assignments. 
 *         Default: &quot;UTF-8&quot;. 
 *         Requirements: This element may appear zero, one, or more times. 
 *     
 *     Example:
 *     
 *      &lt;OutputEncoding&gt;UTF-8&lt;/OutputEncoding&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OpenSearchDescription", propOrder = {
    "shortName",
    "description",
    "tags",
    "contact",
    "url",
    "longName",
    "image",
    "query",
    "developer",
    "attribution",
    "syndicationRight",
    "adultContent",
    "language",
    "outputEncoding",
    "inputEncoding"
})
public class OpenSearchDescription implements NamespacePrefixMapperProvider {

    @XmlElement(name = "ShortName", required = true)
    protected String shortName;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Tags")
    protected String tags;
    @XmlElement(name = "Contact")
    protected String contact;
    @XmlElement(name = "Url", required = true)
    protected List<OpenSearchUrl> url;
    @XmlElement(name = "LongName")
    protected String longName;
    @XmlElement(name = "Image")
    protected List<OpenSearchImage> image;
    @XmlElement(name = "Query")
    protected List<OpenSearchQuery> query;
    @XmlElement(name = "Developer")
    protected String developer;
    @XmlElement(name = "Attribution")
    protected String attribution;
    @XmlElement(name = "SyndicationRight", defaultValue = "open")
    protected String syndicationRight;
    @XmlElement(name = "AdultContent")
    protected String adultContent;
    @XmlElement(name = "Language")
    protected List<String> language;
    @XmlElement(name = "OutputEncoding", defaultValue = "UTF-8")
    protected List<String> outputEncoding;
    @XmlElement(name = "InputEncoding", defaultValue = "UTF-8")
    protected List<String> inputEncoding;
    
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    
    // ============================
    @XmlTransient
    private static final JAXBContext context;

    static {
        try {
            context = JAXBContext.newInstance(OpenSearchDescription.class.getPackage().getName());
        } catch (JAXBException e) {
            throw new RestException("Failed to create JAXBContext for OpenSearchDescription", e);
        }
    }

    public static Marshaller getMarshaller() {
        return JAXBUtils.createMarshaller(context);
    }
    
    public static Unmarshaller getUnmarshaller() {
        return JAXBUtils.createUnmarshaller(context);
    }

    public JAXBNamespacePrefixMapper getNamespacePrefixMapper() {
        JAXBNamespacePrefixMapper mapper = new JAXBNamespacePrefixMapper(RestConstants.NAMESPACE_OPENSEARCH);
        return mapper;
    }
    // ============================

    /**
     * Gets the value of the shortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the value of the shortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the tags property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTags() {
        return tags;
    }

    /**
     * Sets the value of the tags property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTags(String value) {
        this.tags = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContact(String value) {
        this.contact = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the url property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUrl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OpenSearchUrl }
     * 
     * 
     */
    public List<OpenSearchUrl> getUrl() {
        if (url == null) {
            url = new ArrayList<OpenSearchUrl>();
        }
        return this.url;
    }
    
    /**
     * Adds OpenSearchUrl to the list of Open Search Urls.
     * 
     * @param openSearchUrl
     *     possible object is
     *     {@link OpenSearchUrl}
     */
    public void addUrl(OpenSearchUrl openSearchUrl) {
        getUrl().add(openSearchUrl);
    }

    /**
     * Gets the value of the longName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Sets the value of the longName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongName(String value) {
        this.longName = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the image property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OpenSearchImage }
     * 
     * 
     */
    public List<OpenSearchImage> getImage() {
        if (image == null) {
            image = new ArrayList<OpenSearchImage>();
        }
        return this.image;
    }
    
    /**
     * Adds new Image to the list
     * 
     * @param openSearchImage
     *      allowed object is
     *     {@link OpenSearchImage }
     */
    public void addNewImage(OpenSearchImage openSearchImage) {
        getImage().add(openSearchImage);
    }

    /**
     * Gets the value of the query property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the query property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuery().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OpenSearchQuery }
     * 
     * 
     */
    public List<OpenSearchQuery> getQuery() {
        if (query == null) {
            query = new ArrayList<OpenSearchQuery>();
        }
        return this.query;
    }

    /**
     * Adds OpenSearchQuery to the list of Open Search Queries
     * 
     * @param openSearchQuery
     *     possible object is
     *     {@link OpenSearchQuery }
     */
    public void addQuery(OpenSearchQuery openSearchQuery) {
        getQuery().add(openSearchQuery);
    }
    
    /**
     * Gets the value of the developer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * Sets the value of the developer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeveloper(String value) {
        this.developer = value;
    }

    /**
     * Gets the value of the attribution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribution() {
        return attribution;
    }

    /**
     * Sets the value of the attribution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribution(String value) {
        this.attribution = value;
    }

    /**
     * Gets the value of the syndicationRight property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSyndicationRight() {
        return syndicationRight;
    }

    /**
     * Sets the value of the syndicationRight property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSyndicationRight(String value) {
        this.syndicationRight = value;
    }

    /**
     * Gets the value of the adultContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdultContent() {
        return adultContent;
    }

    /**
     * Sets the value of the adultContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdultContent(String value) {
        this.adultContent = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the language property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLanguage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLanguage() {
        if (language == null) {
            language = new ArrayList<String>();
        }
        return this.language;
    }
    
    /**
     * Add "Language". Contains a string that indicates that the search engine
     * supports search results in the specified language.
     * 
     * @param language
     *     allowed object is
     *     {@link String }
     */
    public void addLanguage(String language) {
        getLanguage().add(language);
    }

    /**
     * Gets the value of the outputEncoding property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outputEncoding property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutputEncoding().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOutputEncoding() {
        if (outputEncoding == null) {
            outputEncoding = new ArrayList<String>();
        }
        return this.outputEncoding;
    }
    
    /**
     * Adds OutputEncoding to the list of supported Output Encoding 
     * 
     * @param encoding
     *     allowed object is
     *     {@link String }
     */
    public void addOutputEncoding(String encoding) {
        getOutputEncoding().add(encoding);
    }

    /**
     * Gets the value of the inputEncoding property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inputEncoding property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInputEncoding().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getInputEncoding() {
        if (inputEncoding == null) {
            inputEncoding = new ArrayList<String>();
        }
        return this.inputEncoding;
    }

    /**
     * Adds InputEncoding to the list of supported Input Encoding
     * 
     * @param encoding
     *     allowed object is
     *     {@link String }
     */
    public void addInputEncoding(String encoding) {
        getInputEncoding().add(encoding);
    }
    
    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
