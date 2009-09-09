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
package org.apache.wink.common.internal.providers.entity.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;

import org.apache.wink.common.internal.i18n.Messages;
import org.apache.wink.common.internal.utils.JAXBUtils;
import org.apache.wink.common.model.XmlFormattingOptions;
import org.apache.wink.common.utils.ProviderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@Consumes( {MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.WILDCARD})
@Produces( {MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.WILDCARD})
public class JAXBXmlProvider extends AbstractJAXBProvider implements MessageBodyReader<Object>,
    MessageBodyWriter<Object> {

    private static final Logger logger = LoggerFactory.getLogger(JAXBXmlProvider.class);

    public boolean isReadable(Class<?> type,
                              Type genericType,
                              Annotation[] annotations,
                              MediaType mediaType) {
        return isJAXBObject(type, genericType) && isSupportedMediaType(mediaType);
    }

    public Object readFrom(Class<Object> type,
                           Type genericType,
                           Annotation[] annotations,
                           MediaType mediaType,
                           MultivaluedMap<String, String> httpHeaders,
                           InputStream entityStream) throws IOException, WebApplicationException {

        Unmarshaller unmarshaller = null;
        Object unmarshaledResource = null;
        try {
            unmarshaller = getUnmarshaller(type, mediaType);
            if (type.isAnnotationPresent(XmlRootElement.class))
                unmarshaledResource = unmarshaller.unmarshal(entityStream);
            else
                unmarshaledResource =
                    unmarshaller.unmarshal(new StreamSource(entityStream), type).getValue();

        } catch (JAXBException e) {
            logger.error(Messages.getMessage("jaxbFailToUnmarshal"), type.getName());
            throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
        }
        return unmarshaledResource;
    }

    public long getSize(Object t,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType) {
        return -1;
    }

    public boolean isWriteable(Class<?> type,
                               Type genericType,
                               Annotation[] annotations,
                               MediaType mediaType) {
        return isJAXBObject(type, genericType) && isSupportedMediaType(mediaType);
    }

    public void writeTo(Object t,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException, WebApplicationException {
        try {
            Marshaller marshaller = getMarshaller(type, mediaType);

            marshaller.setProperty(Marshaller.JAXB_ENCODING, ProviderUtils.getCharset(mediaType));

            Object entityToMarshal = getEntityToMarshal(t, type);

            JAXBUtils.setXmlFormattingOptions(marshaller, XmlFormattingOptions
                .getDefaultXmlFormattingOptions());

            // Use an OutputStream directly instead of a Writer for performance.
            marshaller.marshal(entityToMarshal, entityStream);
        } catch (JAXBException e) {
            logger.error(Messages.getMessage("jaxbFailToMarshal"), type.getName());
            throw new WebApplicationException(e);
        }
    }

}
