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

import org.omnaest.evaluation.webservice.hypermedia.ResourceContainerHypermediaController;
import org.omnaest.evaluation.webservice.resources.ResourceContainer;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Factory for the resource classes.
 * 
 * @author Omnaest
 */
public class ResourceFactory
{
  /* ********************************************** Variables ********************************************** */
  protected String address = null;
  
  /* ********************************************** Methods ********************************************** */
  
  /**
   * @param address
   */
  public ResourceFactory( String address )
  {
    super();
    this.address = address;
  }
  
  /**
   * Returns an proxy for {@link ResourceContainer}
   * 
   * @return
   */
  public ResourceContainer newResourceContainer()
  {
    //
    Client client = new Client();
    
    //
    ResourceContainer resourceContainer = client.view( this.address, ResourceContainerHypermediaController.class );
    
    //
    return resourceContainer;
  }
  
  /**
   * @see WebResource
   * @param resourcePath
   * @return
   */
  public WebResource newWebResource( String resourcePath )
  {
    //
    WebResource retval = null;
    
    //
    ClientConfig clientConfig = new DefaultClientConfig();
    
    //
    Client client = Client.create( clientConfig );
    retval = client.resource( this.address + "/" + resourcePath );
    
    //
    return retval;
  }
}
