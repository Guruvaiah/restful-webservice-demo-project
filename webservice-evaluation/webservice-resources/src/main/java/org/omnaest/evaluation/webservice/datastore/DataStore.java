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
package org.omnaest.evaluation.webservice.datastore;

import java.util.Map;

/**
 * Simple key value {@link DataStore}
 * 
 * @author Omnaest
 */
public interface DataStore
{
  /**
   * Puts a key value pair into the {@link DataStore}.
   * 
   * @param key
   * @param value
   */
  public void put( String key, String value );
  
  /**
   * Gets a value for a given key from the {@link DataStore}
   * 
   * @param key
   * @return
   */
  public String get( String key );
  
  /**
   * Returns a new not thread safe Map containing all entries within the {@link DataStore}.
   * 
   * @return
   */
  public Map<String, String> getAll();
  
  /**
   * Sets the content of the {@link DataStore}. All previously stored key and value pairs will be discarded.
   * 
   * @param map
   */
  public void setAll( Map<String, String> map );
}
