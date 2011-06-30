package org.omnaest.evaluation.webservice.graph;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Dummy object which generates a deep object graph.
 * 
 * @author Omnaest
 */
@XmlRootElement
public class ArbitraryObjectGraph
{
  /* ********************************************** Variables ********************************************** */
  protected List<ArbitraryObjectGraph> arbitraryObjectGraphList = new ArrayList<ArbitraryObjectGraph>();
  
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
      this.arbitraryObjectGraphList.add( new ArbitraryObjectGraph( graphDept - 1 ) );
      this.arbitraryObjectGraphList.add( new ArbitraryObjectGraph( graphDept - 1 ) );
    }
  }
  
  public ArbitraryObjectGraph()
  {
    super();
  }
}
