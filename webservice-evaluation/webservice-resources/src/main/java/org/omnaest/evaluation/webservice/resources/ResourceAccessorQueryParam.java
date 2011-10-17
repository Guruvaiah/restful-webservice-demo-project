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
package org.omnaest.evaluation.webservice.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * This resource uses {@link QueryParam} annotations to retrieve the method parameters.
 * 
 * @author Omnaest
 */
@Path("ResourceAccessorQueryParam")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public interface ResourceAccessorQueryParam
{
  
  /**
   * Gets a previously saved value for a given key. The key is determined by the last token of the uri.
   * 
   * @see #setValue(String, String)
   * @see #setValuePost(String, String)
   * @param message
   * @return
   */
  @GET
  public String getValue( @QueryParam("key") String key );
  
  /**
   * Sets a message to be stored using the http body as value and the last token of the uri as key.
   * 
   * @see #getValue(String)
   * @param value
   */
  @PUT
  public void setValue( @QueryParam("key") String key, String value );
  
  /**
   * Deletes the stored value.
   */
  @DELETE
  public void clearValue( @QueryParam("key") String key );
  
}
