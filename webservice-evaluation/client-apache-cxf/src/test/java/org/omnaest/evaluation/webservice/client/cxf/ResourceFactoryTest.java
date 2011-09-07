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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omnaest.evaluation.webservice.graph.ArbitraryObjectGraph;
import org.omnaest.evaluation.webservice.resources.ResourceAccessorPathParam;
import org.omnaest.evaluation.webservice.resources.ResourceAccessorQueryParam;
import org.omnaest.evaluation.webservice.resources.ResourceArbitraryObjectGraph;
import org.omnaest.evaluation.webservice.resources.ResourceContainer;
import org.omnaest.utils.xml.JAXBMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rest-client.xml")
public class ResourceFactoryTest
{
  
  /* ********************************************** Beans / Services ********************************************** */
  @Autowired
  protected ResourceContainer            resourceContainer            = null;
  
  @Autowired
  protected ResourceAccessorPathParam    resourceAccessorPathParam    = null;
  
  @Autowired
  protected ResourceAccessorQueryParam   resourceAccessorQueryParam   = null;
  
  @Autowired
  protected ResourceArbitraryObjectGraph resourceArbitraryObjectGraph = null;
  
  /* ********************************************** Methods ********************************************** */
  @Before
  public void setUp() throws Exception
  {
    //
    Assert.assertNotNull( this.resourceContainer );
    Assert.assertNotNull( this.resourceAccessorPathParam );
    Assert.assertNotNull( this.resourceAccessorQueryParam );
    Assert.assertNotNull( this.resourceArbitraryObjectGraph );
  }
  
  @Test
  public void testNewResourceContainerSendXMLRecieveXML()
  {
    //
    Map<String, String> map = new HashMap<String, String>();
    map.put( "key1", "value1" );
    map.put( "key2", "value2" );
    
    //
    {
      //
      WebClient.client( this.resourceContainer ).reset();
      WebClient.client( this.resourceContainer ).accept( MediaType.APPLICATION_XML_TYPE );
      WebClient.client( this.resourceContainer ).type( MediaType.APPLICATION_XML_TYPE );
      this.resourceContainer.setContent( JAXBMap.newInstance( map ) );
      
      //
      assertEquals( map, this.resourceContainer.getContent() );
    }
    
  }
  
  @Test
  public void testNewResourceContainerSendXMLRecieveJSON()
  {
    //
    Map<String, String> map = new HashMap<String, String>();
    map.put( "key1", "value1" );
    map.put( "key2", "value2" );
    
    //
    {
      //
      WebClient.client( this.resourceContainer ).reset();
      WebClient.client( this.resourceContainer ).accept( MediaType.APPLICATION_JSON_TYPE );
      WebClient.client( this.resourceContainer ).type( MediaType.APPLICATION_XML_TYPE );
      this.resourceContainer.setContent( JAXBMap.newInstance( map ) );
      
      //
      assertEquals( map, this.resourceContainer.getContent() );
    }
    
  }
  
  @Test
  public void testNewResourceContainerSendJSONRevieveJSON()
  {
    //
    Map<String, String> map = new HashMap<String, String>();
    map.put( "key1", "value1" );
    map.put( "key2", "value2" );
    
    //
    {
      //
      WebClient.client( this.resourceContainer ).reset();
      WebClient.client( this.resourceContainer ).accept( MediaType.APPLICATION_JSON_TYPE );
      WebClient.client( this.resourceContainer ).type( MediaType.APPLICATION_JSON_TYPE );
      this.resourceContainer.setContent( JAXBMap.newInstance( map ) );
      
      //
      assertEquals( map, this.resourceContainer.getContent() );
    }
  }
  
  @Test
  public void testNewResourceAccessorPathParam()
  {
    //
    this.resourceAccessorPathParam.setValue( "firstKey", "firstValue" );
    
    //
    assertEquals( this.resourceAccessorPathParam.getValue( "firstKey" ), "firstValue" );
  }
  
  @Test
  public void testNewResourceAccessorQueryParam()
  {
    //
    String key = "some key";
    String value = "some value";
    
    this.resourceAccessorQueryParam.setValue( key, value );
    
    //
    assertEquals( "some value", this.resourceAccessorQueryParam.getValue( "some key" ) );
  }
  
  @Test
  public void testNewResourceAccessorArbitraryObjectGraphRevieveXML()
  {
    //
    {
      int graphDept = 5;
      WebClient.client( this.resourceArbitraryObjectGraph ).reset();
      WebClient.client( this.resourceArbitraryObjectGraph ).accept( MediaType.APPLICATION_XML_TYPE );
      ArbitraryObjectGraph objectGraph = this.resourceArbitraryObjectGraph.getArbitraryObjectGraph( graphDept );
      assertNotNull( objectGraph );
      assertEquals( 2, objectGraph.getArbitraryObjectGraphList().size() );
    }
  }
  
  @Test
  public void testNewResourceAccessorArbitraryObjectGraphRecieveJSON()
  {
    //
    {
      WebClient.client( this.resourceArbitraryObjectGraph ).reset();
      WebClient.client( this.resourceArbitraryObjectGraph ).accept( MediaType.APPLICATION_JSON_TYPE );
      int graphDept = 5;
      ArbitraryObjectGraph objectGraph = this.resourceArbitraryObjectGraph.getArbitraryObjectGraph( graphDept );
      assertNotNull( objectGraph );
      assertEquals( 2, objectGraph.getArbitraryObjectGraphList().size() );
    }
  }
  
  public void setResourceContainer( ResourceContainer resourceContainer )
  {
    this.resourceContainer = resourceContainer;
  }
  
  public void setResourceAccessorPathParam( ResourceAccessorPathParam resourceAccessorPathParam )
  {
    this.resourceAccessorPathParam = resourceAccessorPathParam;
  }
  
  public void setResourceArbitraryObjectGraph( ResourceArbitraryObjectGraph resourceArbitraryObjectGraph )
  {
    this.resourceArbitraryObjectGraph = resourceArbitraryObjectGraph;
  }
  
  public void setResourceAccessorQueryParam( ResourceAccessorQueryParam resourceAccessorQueryParam )
  {
    this.resourceAccessorQueryParam = resourceAccessorQueryParam;
  }
  
}
