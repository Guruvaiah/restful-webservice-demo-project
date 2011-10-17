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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

import org.omnaest.evaluation.webservice.graph.ArbitraryObjectGraph;

/**
 * Resource for some {@link ArbitraryObjectGraph}
 * 
 * @author Omnaest
 */
@Path("ResourceArbitraryObjectGraph")
public interface ResourceArbitraryObjectGraph
{
  /* ********************************************** Variables ********************************************** */
  @XmlRootElement
  @XmlEnum(value = String.class)
  public static enum Parameter
  {
    ONE,
    TWO,
    THREE;
  }
  
  @XmlRootElement
  public static class ParameterWrapper
  {
    /* ********************************************** Variables ********************************************** */
    @XmlElement
    private Parameter parameter = null;
    
    /* ********************************************** Methods ********************************************** */
    
    public ParameterWrapper()
    {
      super();
    }
    
    public ParameterWrapper( Parameter parameter )
    {
      super();
      this.parameter = parameter;
    }
    
    public Parameter getParameter()
    {
      return this.parameter;
    }
    
    public void setParameter( Parameter parameter )
    {
      this.parameter = parameter;
    }
    
  }
  
  /* ********************************************** Methods ********************************************** */
  
  @GET
  @Path("getArbitraryObjectGraphXMLOnly")
  @Produces(MediaType.APPLICATION_XML)
  public ArbitraryObjectGraph getArbitraryObjectGraphXMLOnly( @QueryParam("graphDept") int graphDept );
  
  @GET
  @Path("getArbitraryObjectGraphJSONOnly")
  @Produces(MediaType.APPLICATION_JSON)
  public ArbitraryObjectGraph getArbitraryObjectGraphJSONOnly( @QueryParam("graphDept") int graphDept );
  
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public ArbitraryObjectGraph getArbitraryObjectGraph( @QueryParam("graphDept") int graphDept );
  
  @GET
  @Produces({ MediaType.APPLICATION_XML })
  @Consumes({ MediaType.APPLICATION_XML })
  @Path("getParameter")
  public Parameter getParameter( @QueryParam("parameter") Parameter parameter );
}
