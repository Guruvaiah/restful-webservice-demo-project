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

import org.omnaest.evaluation.webservice.datastore.DataStore;
import org.omnaest.evaluation.webservice.resources.ResourceAccessorQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Every web service interface has to have an own implementation marked with {@link Component} even if there is a shared
 * implementation.
 * 
 * @see ResourceAccessorQueryParamImpl
 * @see ResourceAccessorBasic
 * @author Omnaest
 */
@Component
@Scope("prototype")
public class ResourceAccessorQueryParamImpl implements ResourceAccessorQueryParam
{
  /* ********************************************** Beans / Services ********************************************** */
  @Autowired
  protected DataStore dataStore = null;
  
  /* ********************************************** Methods ********************************************** */
  
  protected void setDataStore( DataStore dataStore )
  {
    this.dataStore = dataStore;
  }
  
  @Override
  public String getValue( String key )
  {
    return this.dataStore.get( key );
  }
  
  @Override
  public void setValue( String key, String value )
  {
    this.dataStore.put( key, value );
  }
  
  @Override
  public void clearValue( String key )
  {
    this.dataStore.put( key, null );
  }
}
