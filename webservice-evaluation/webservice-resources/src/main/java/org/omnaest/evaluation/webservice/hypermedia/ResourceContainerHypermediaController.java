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
package org.omnaest.evaluation.webservice.hypermedia;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.omnaest.evaluation.webservice.resources.ResourceContainer;
import org.omnaest.evaluation.webservice.resources.internal.ResourceContainerImpl;
import org.omnaest.utils.xml.JAXBMap;

import com.sun.jersey.core.hypermedia.Action;
import com.sun.jersey.core.hypermedia.HypermediaController;
import com.sun.jersey.core.hypermedia.HypermediaController.LinkType;

@HypermediaController(model = ResourceContainer.class, linkType = LinkType.LINK_HEADERS)
public class ResourceContainerHypermediaController extends ResourceContainerImpl
{
  
  @Consumes(MediaType.APPLICATION_XML)
  @Action("setContent")
  @Override
  public void setContent( JAXBMap<String, String> map )
  {
    super.setContent( map );
  }
  
  @Action("getContent")
  @Override
  public JAXBMap<String, String> getContent()
  {
    return super.getContent();
  }
  
}
