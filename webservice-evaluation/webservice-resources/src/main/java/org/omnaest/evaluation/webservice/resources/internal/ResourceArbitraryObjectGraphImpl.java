package org.omnaest.evaluation.webservice.resources.internal;

import org.omnaest.evaluation.webservice.graph.ArbitraryObjectGraph;
import org.omnaest.evaluation.webservice.resources.ResourceArbitraryObjectGraph;
import org.springframework.stereotype.Service;

/**
 * @see ResourceArbitraryObjectGraph
 * @see ArbitraryObjectGraph
 * @author Omnaest
 */
@Service
public class ResourceArbitraryObjectGraphImpl implements ResourceArbitraryObjectGraph
{
  @Override
  public ArbitraryObjectGraph getArbitraryObjectGraphXMLOnly( int graphDept )
  {
    return new ArbitraryObjectGraph( graphDept );
  }
  
  @Override
  public ArbitraryObjectGraph getArbitraryObjectGraphJSONOnly( int graphDept )
  {
    return new ArbitraryObjectGraph( graphDept );
  }
  
  @Override
  public ArbitraryObjectGraph getArbitraryObjectGraph( int graphDept )
  {
    return new ArbitraryObjectGraph( graphDept );
  }
}
