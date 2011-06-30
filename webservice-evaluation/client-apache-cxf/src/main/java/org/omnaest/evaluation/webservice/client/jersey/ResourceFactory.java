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

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.omnaest.evaluation.webservice.resources.ResourceAccessorPathParam;
import org.omnaest.evaluation.webservice.resources.ResourceAccessorQueryParam;
import org.omnaest.evaluation.webservice.resources.ResourceArbitraryObjectGraph;
import org.omnaest.evaluation.webservice.resources.ResourceContainer;

/**
 * Factory for the resource classes.
 * 
 * @author Omnaest
 */
public class ResourceFactory
{
  
  /**
   * Returns an proxy for {@link ResourceContainer}
   * 
   * @param uri
   * @return
   */
  public static ResourceContainer newResourceContainer( String uri )
  {
    return JAXRSClientFactory.create( uri, ResourceContainer.class );
  }
  
  /**
   * Returns an proxy for {@link ResourceAccessorPathParam}
   * 
   * @param uri
   * @return
   */
  public static ResourceAccessorPathParam newResourceAccessorPathParam( String uri )
  {
    return JAXRSClientFactory.create( uri, ResourceAccessorPathParam.class );
  }
  
  /**
   * Returns an proxy for {@link ResourceAccessorQueryParam}
   * 
   * @param uri
   * @return
   */
  public static ResourceAccessorQueryParam newResourceAccessorQueryParam( String uri )
  {
    return JAXRSClientFactory.create( uri, ResourceAccessorQueryParam.class );
  }
  
  /**
   * Returns an proxy for {@link ResourceArbitraryObjectGraph}
   * 
   * @param uri
   * @return
   */
  public static ResourceArbitraryObjectGraph newResourceArbitraryObjectGraph( String uri )
  {
    return JAXRSClientFactory.create( uri, ResourceArbitraryObjectGraph.class );
  }
  
}
