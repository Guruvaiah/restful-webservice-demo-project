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
package org.omnaest.evaluation.webservice.client.jersey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.omnaest.evaluation.webservice.resources.ResourceAccessorPathParam;
import org.omnaest.evaluation.webservice.resources.ResourceAccessorQueryParam;
import org.omnaest.evaluation.webservice.resources.ResourceContainer;
import org.omnaest.utils.structure.element.ElementHolder;
import org.omnaest.utils.webservice.rest.RestClientFactoryJersey;
import org.omnaest.utils.xml.JAXBMap;

import com.sun.jersey.api.client.WebResource;

/**
 * @see ResourceFactory
 * @author Omnaest
 */
public class ResourceFactoryTest
{
  /* ********************************************** Constants ********************************************** */
  public final static String URL_BASE        = "http://localhost:8866/webapp-jersey"; //"http://localhost:8082/webapp-jersey";
                                                                                      
  /* ********************************************** Variables ********************************************** */
  protected ResourceFactory  resourceFactory = new ResourceFactory( URL_BASE );
  
  /* ********************************************** Methods ********************************************** */
  
  @Before
  public void setUp() throws Exception
  {
  }
  
  @Ignore("This does not work yet")
  @Test
  public void testNewResourceContainerWithHypermediaController()
  {
    //    
    ResourceContainer resourceContainer = this.resourceFactory.newResourceContainer();
    
    //
    Map<String, String> map = new HashMap<String, String>();
    map.put( "key1", "value1" );
    map.put( "key2", "value2" );
    
    //
    resourceContainer.setContent( JAXBMap.newInstance( map ) );
    
    //
    Map<String, String> content = resourceContainer.getContent();
    
    //
    assertEquals( map, content );
  }
  
  @Test
  public void testRegularJerseyWebResource()
  {
    //
    WebResource webResource = this.resourceFactory.newWebResource( "ResourceAccessorPathParam" );
    
    //
    webResource.accept( MediaType.TEXT_PLAIN );
    webResource.type( MediaType.TEXT_PLAIN );
    
    //
    webResource = webResource.path( "key5" );
    
    webResource.put( "value5" );
    
  }
  
  @Test
  //@Ignore("working but use proxy instead")
  public void testNewWebResource()
  {
    //
    WebResource webResource = this.resourceFactory.newWebResource( "ResourceArbitraryObjectGraph" );
    
    //
    webResource.accept( MediaType.APPLICATION_XML_TYPE );
    webResource.type( MediaType.APPLICATION_XML_TYPE );
    
    //
    String resourceArbitraryObjectGraphString = webResource.queryParam( "graphDept", "5" ).get( String.class );
    assertNotNull( resourceArbitraryObjectGraphString );
  }
  
  @Test
  public void testProxyPathParam()
  {
    RestClientFactoryJersey restClientFactoryJersey = new RestClientFactoryJersey( URL_BASE );
    
    ResourceAccessorPathParam resourceAccessorPathParam = restClientFactoryJersey.newRestClient( ResourceAccessorPathParam.class );
    assertNotNull( resourceAccessorPathParam );
    
    resourceAccessorPathParam.setValue( "key1", "value1" );
    
    String value = resourceAccessorPathParam.getValue( "key1" );
    assertNotNull( value );
    assertEquals( "value1", value );
  }
  
  @Test
  public void testProxyQueryParam()
  {
    RestClientFactoryJersey restClientFactoryJersey = new RestClientFactoryJersey( URL_BASE );
    
    ResourceAccessorQueryParam resourceAccessorQueryParam = restClientFactoryJersey.newRestClient( ResourceAccessorQueryParam.class );
    assertNotNull( resourceAccessorQueryParam );
    
    resourceAccessorQueryParam.setValueXML( "key2", new ElementHolder<String>( "value2" ) );
    
    String value = resourceAccessorQueryParam.getValue( "key2" );
    assertNotNull( value );
    assertEquals( "value2", value );
  }
  
}
