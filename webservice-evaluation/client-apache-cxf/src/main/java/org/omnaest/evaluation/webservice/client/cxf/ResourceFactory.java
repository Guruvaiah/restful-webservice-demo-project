/*******************************************************************************
 * Copyright 2011 Danny Kunz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.omnaest.evaluation.webservice.client.cxf;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.omnaest.evaluation.webservice.resources.ResourceAccessorPathParam;
import org.omnaest.evaluation.webservice.resources.ResourceAccessorQueryParam;
import org.omnaest.evaluation.webservice.resources.ResourceArbitraryObjectGraph;
import org.omnaest.evaluation.webservice.resources.ResourceContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Factory for the resource classes.
 * 
 * @author Omnaest
 */
@Configuration
public class ResourceFactory
{
  /* ********************************************** Variables ********************************************** */
  protected String  address          = null;
  protected boolean prepareMediaType = true;
  
  /* ********************************************** Methods ********************************************** */
  
  /**
   * Returns an proxy for {@link ResourceContainer}
   * 
   * @return
   */
  @Bean
  public ResourceContainer newResourceContainer()
  {
    return this.createRESTProxy( ResourceContainer.class, MediaType.APPLICATION_XML_TYPE );
  }
  
  /**
   * Returns an proxy for {@link ResourceAccessorPathParam}
   * 
   * @return
   */
  @Bean
  public ResourceAccessorPathParam newResourceAccessorPathParam()
  {
    return this.createRESTProxy( ResourceAccessorPathParam.class, MediaType.TEXT_PLAIN_TYPE );
  }
  
  /**
   * Returns an proxy for {@link ResourceAccessorQueryParam}
   * 
   * @return
   */
  @Bean
  public ResourceAccessorQueryParam newResourceAccessorQueryParam()
  {
    return this.createRESTProxy( ResourceAccessorQueryParam.class, MediaType.TEXT_PLAIN_TYPE );
  }
  
  /**
   * Returns an proxy for {@link ResourceArbitraryObjectGraph}
   * 
   * @return
   */
  @Bean
  public ResourceArbitraryObjectGraph newResourceArbitraryObjectGraph()
  {
    return this.createRESTProxy( ResourceArbitraryObjectGraph.class, MediaType.APPLICATION_XML_TYPE );
  }
  
  /**
   * Creates a REST proxy which accepts the given {@link MediaType}
   * 
   * @param type
   * @param mediaType
   * @return
   */
  protected <T> T createRESTProxy( Class<T> type, MediaType mediaType )
  {
    //
    T proxy = JAXRSClientFactory.create( this.address, type );
    
    //
    System.out.println( this.prepareMediaType );
    if ( this.prepareMediaType )
    {
      WebClient.client( proxy ).accept( mediaType );
    }
    
    //
    return proxy;
  }
  
  /**
   * @param address
   */
  public void setAddress( String address )
  {
    this.address = address;
  }
  
  /**
   * @param prepareMediaType
   * @return
   */
  public ResourceFactory setPrepareMediaType( boolean prepareMediaType )
  {
    //
    this.prepareMediaType = prepareMediaType;
    
    //
    return this;
  }
  
  /**
   * @param prepareMediaType
   */
  public void setPrepareMediaType( String prepareMediaType )
  {
    this.prepareMediaType = Boolean.valueOf( prepareMediaType );
  }
  
}
