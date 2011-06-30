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
package org.omnaest.evaluation.webservice.resources.internal;

import java.util.Map;

import org.omnaest.evaluation.webservice.datastore.DataStore;
import org.omnaest.evaluation.webservice.resources.ResourceAccessorPathParam;
import org.omnaest.evaluation.webservice.resources.ResourceContainer;
import org.omnaest.utils.xml.JAXBMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author Omnaest
 * @see ResourceAccessorPathParam
 */
@Service("resourceAccessorPathParam")
@Scope("prototype")
public class ResourceContainerImpl implements ResourceContainer
{
  /* ********************************************** Variables ********************************************** */
  @Autowired
  protected DataStore        dataStore        = null;
  
  @Autowired
  protected ResourceAccessorPathParam resourceAccessorPathParam = null;
  
  /* ********************************************** Methods ********************************************** */

  @Override
  public void setContent( Map<String, String> map )
  {
    this.dataStore.setAll( map );
  }
  
  @Override
  public ResourceAccessorPathParam resourceAccessorPathParam()
  {
    return this.resourceAccessorPathParam;
  }
  
  protected void setDataStore( DataStore dataStore )
  {
    this.dataStore = dataStore;
  }
  
  protected void setResourceAccessor( ResourceAccessorPathParam resourceAccessorPathParam )
  {
    this.resourceAccessorPathParam = resourceAccessorPathParam;
  }
  
  @Override
  public Map<String, String> getContent()
  {
    // 
    JAXBMap<String, String> jaxbMap = JAXBMap.newInstance( this.dataStore.getAll() );
    return jaxbMap;
  }
  
}
