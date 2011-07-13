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
package org.omnaest.evaluation.webservice.graph;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Dummy object which generates a deep object graph.
 * 
 * @author Omnaest
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ArbitraryObjectGraph
{
  /* ********************************************** Variables ********************************************** */
  @XmlElement
  protected ArbitraryObjectGraph arbitraryObjectGraph1 = null;
  
  @XmlElement
  protected ArbitraryObjectGraph arbitraryObjectGraph2 = null;
  
  /* ********************************************** Methods ********************************************** */
  
  /**
   * Generates an object binary tree with 2^graphDept objects at the bottom layer and one parent for two child objects for each
   * layer above.
   * 
   * @param graphDept
   */
  public ArbitraryObjectGraph( int graphDept )
  {
    //
    super();
    
    //
    if ( graphDept > 0 )
    {
      this.arbitraryObjectGraph1 = new ArbitraryObjectGraph( graphDept - 1 );
      this.arbitraryObjectGraph2 = new ArbitraryObjectGraph( graphDept - 1 );
    }
  }
  
  /**
   * 
   */
  public ArbitraryObjectGraph()
  {
    super();
  }
  
  /**
   * @return
   */
  public List<ArbitraryObjectGraph> getArbitraryObjectGraphList()
  {
    return Arrays.asList( this.arbitraryObjectGraph1, this.arbitraryObjectGraph2 );
  }
  
}
