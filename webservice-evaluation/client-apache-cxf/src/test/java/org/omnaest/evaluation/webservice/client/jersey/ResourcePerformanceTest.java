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

import java.io.File;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Before;
import org.junit.Test;
import org.omnaest.evaluation.webservice.resources.ResourceAccessorPathParam;
import org.omnaest.evaluation.webservice.resources.ResourceArbitraryObjectGraph;
import org.omnaest.utils.io.XLSFile;
import org.omnaest.utils.io.XLSFile.TableRow;
import org.omnaest.utils.time.DurationCapture;

public class ResourcePerformanceTest
{
  private final static String URL_BASE = "http://localhost:8082/webapp-jersey";
  
  @Before
  public void setUp() throws Exception
  {
  }
  
  @Test
  public void testNewResourceAccessorPathParam()
  {
    //
    ResourceAccessorPathParam resourceAccessorPathParam = ResourceFactory.newResourceAccessorPathParam( URL_BASE );
    
    //
    DurationCapture durationCapture = DurationCapture.newInstance().startTimeMeasurement();
    
    int counter = 0;
    while ( durationCapture.getInterimTimeInMilliseconds() < 1000 )
    {
      resourceAccessorPathParam.setValue( "firstKey", "firstValue" );
      counter++;
    }
    long durationInMilliseconds = durationCapture.stopTimeMeasurement().getDurationInMilliseconds();
    
    //
    System.out.println( "request counter:" + counter );
    System.out.println( "whole duration:" + durationInMilliseconds + " [ms]" );
    System.out.println( String.format( "time for single request: %2.2f [ms]", durationInMilliseconds * 1.0 / counter ) );
  }
  
  @Test
  public void testNewResourceArbitraryObjectGraph()
  {
    //
    ResourceArbitraryObjectGraph resourceArbitraryObjectGraph = ResourceFactory.newResourceArbitraryObjectGraph( URL_BASE );
    
    //
    XLSFile xlsFile = new XLSFile( new File( "performance.xls" ) );
    {
      TableRow tableRow = new TableRow( "graphDept", "durationInMillisecondsXML", "durationPerRequestInMillisecondsJSON" );
      xlsFile.getTableRowList().add( tableRow );
    }
    
    //
    for ( Integer graphDept : Arrays.asList( 0, 1, 2, 3, 15, 16, 17, 18 ) )
    {
      //
      double durationPerRequestInMillisecondsJSON = 0.0;
      double durationPerRequestInMillisecondsXML = 0.0;
      
      //
      for ( MediaType acceptType : Arrays.asList( MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_JSON_TYPE ) )
      {
        //
        WebClient.client( resourceArbitraryObjectGraph ).accept( acceptType );
        
        //
        DurationCapture durationCapture = DurationCapture.newInstance().startTimeMeasurement();
        int counter = 0;
        while ( durationCapture.getInterimTimeInMilliseconds() < 5000 )
        {
          resourceArbitraryObjectGraph.getArbitraryObjectGraph( graphDept );
          counter++;
        }
        long durationInMilliseconds = durationCapture.stopTimeMeasurement().getDurationInMilliseconds();
        
        double durationPerRequestInMilliseconds = durationInMilliseconds * 1.0 / counter;
        
        //
        if ( MediaType.APPLICATION_XML_TYPE.equals( acceptType ) )
        {
          durationPerRequestInMillisecondsXML = durationPerRequestInMilliseconds;
        }
        else if ( MediaType.APPLICATION_JSON_TYPE.equals( acceptType ) )
        {
          durationPerRequestInMillisecondsJSON = durationPerRequestInMilliseconds;
        }
        
        //
        System.out.println();
        System.out.println( "acceptType:" + acceptType );
        System.out.println( "graphDept:" + graphDept );
        System.out.println( "request counter:" + counter );
        System.out.println( "whole duration:" + durationInMilliseconds + " [ms]" );
        System.out.println( String.format( "time for single request: %2.2f [ms]", durationPerRequestInMilliseconds ) );
        
      }
      
      //
      TableRow tableRow = new TableRow( "" + graphDept, String.format( "%2.2f", durationPerRequestInMillisecondsXML ),
                                        String.format( "%2.2f", durationPerRequestInMillisecondsJSON ) );
      xlsFile.getTableRowList().add( tableRow );
    }
    
    //
    xlsFile.store();
  }
}
