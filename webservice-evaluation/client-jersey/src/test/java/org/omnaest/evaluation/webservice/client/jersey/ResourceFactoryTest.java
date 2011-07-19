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
import org.omnaest.evaluation.webservice.resources.ResourceContainer;
import org.omnaest.utils.xml.JAXBMap;

import com.sun.jersey.api.client.WebResource;

/**
 * @see ResourceFactory
 * @author Omnaest
 */
public class ResourceFactoryTest
{
  /* ********************************************** Constants ********************************************** */
  private final static String URL_BASE        = "http://localhost:8866/webapp-jersey"; //"http://localhost:8082/webapp-jersey";
                                                                                       
  /* ********************************************** Variables ********************************************** */
  protected ResourceFactory   resourceFactory = new ResourceFactory( URL_BASE );
  
  /* ********************************************** Methods ********************************************** */
  
  @Before
  public void setUp() throws Exception
  {
  }
  
  @Ignore
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
  public void testNewWebResource()
  {
    //
    WebResource webResource = this.resourceFactory.newWebResource( "ResourceArbitraryObjectGraph" );
    
    //
    webResource.accept( MediaType.APPLICATION_XML_TYPE );
    webResource.type( MediaType.APPLICATION_XML_TYPE );
    
    //
    String resourceArbitraryObjectGraphString = webResource.get( String.class );
    assertNotNull( resourceArbitraryObjectGraphString );
  }
  
}
