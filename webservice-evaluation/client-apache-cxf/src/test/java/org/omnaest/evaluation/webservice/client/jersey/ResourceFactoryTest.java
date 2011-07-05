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

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
  public void testNewResourceContainer()
  {
    //
    Map<String, String> map = new HashMap<String, String>();
    map.put( "key1", "value1" );
    map.put( "key2", "value2" );
    
    //
    this.resourceContainer.setContent( JAXBMap.newInstance( map ) );
    
    //
    Map<String, String> content = resourceContainer.getContent();
    
    //
    assertEquals( map, content );
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
  public void testNewResourceAccessorArbitraryObjectGraph()
  {
    //
    
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
