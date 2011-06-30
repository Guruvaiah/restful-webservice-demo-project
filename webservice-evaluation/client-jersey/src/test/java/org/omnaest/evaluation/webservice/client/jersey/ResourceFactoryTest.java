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

import org.junit.Before;
import org.junit.Test;
import org.omnaest.evaluation.webservice.resources.ResourceContainer;
import org.omnaest.utils.xml.JAXBMap;

/**
 * @see ResourceFactory
 * @author Omnaest
 */
public class ResourceFactoryTest
{
  private final static String URL_BASE = "http://localhost:8082/";
  
  @Before
  public void setUp() throws Exception
  {
  }
  
  @Test
  public void testNewResourceContainer()
  {
    //
    ResourceContainer resourceContainer = ResourceFactory.newResourceContainer( URL_BASE + "ResourceContainer" );
    
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
  
}
