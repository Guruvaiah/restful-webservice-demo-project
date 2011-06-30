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
package org.omnaest.evaluation.webservice.datastore.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.omnaest.evaluation.webservice.datastore.DataStore;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Simple {@link DataStore} with an underlying {@link ConcurrentHashMap}.
 * 
 * @author Omnaest
 */
@Component
@Scope("singleton")
public class DataStoreMapImpl implements DataStore
{
  /* ********************************************** Variables ********************************************** */
  protected Map<String, String> map = new ConcurrentHashMap<String, String>();
  
  /* ********************************************** Methods ********************************************** */
  @Override
  public void put( String key, String value )
  {
    if ( key != null )
    {
      this.map.put( key, value );
    }
  }
  
  @Override
  public String get( String key )
  {
    return this.map.get( key );
  }
  
  @Override
  public Map<String, String> getAll()
  {
    return new HashMap<String, String>( this.map );
  }
  
  @Override
  public void setAll( Map<String, String> map )
  {
    this.map.clear();
    this.map.putAll( map );
  }
  
}
