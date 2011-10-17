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
package org.omnaest.evaluation.webservice.client.cxf;

import java.io.File;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omnaest.evaluation.webservice.resources.ResourceArbitraryObjectGraph;
import org.omnaest.utils.io.XLSFile;
import org.omnaest.utils.io.XLSFile.TableRow;
import org.omnaest.utils.time.DurationCapture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rest-client.xml")
public class ResourcePerformanceTest
{
  
  /* ********************************************** Beans / Services ********************************************** */
  @Autowired
  protected ResourceFactory resourceFactory = null;
  
  /* ********************************************** Methods ********************************************** */
  
  @Before
  public void setUp() throws Exception
  {
  }
  
  @Test
  public void testNewResourceAccessorPathParam()
  {
    //
    DurationCapture durationCapture = DurationCapture.newInstance().startTimeMeasurement();
    
    int counter = 0;
    while ( durationCapture.getInterimTimeInMilliseconds() < 1000 )
    {
      this.resourceFactory.newResourceAccessorQueryParam().setValue( "firstKey", "value" );
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
    final int cycleDuration = 5000;
    Integer[] graphDepths = { 0, 1, 2, 3, 10, 11, 12, 13 };
    
    //
    XLSFile xlsFile = new XLSFile( new File( "performance.xls" ) );
    {
      TableRow tableRow = new TableRow( "graphDept", "durationInMillisecondsXML", "durationPerRequestInMillisecondsJSON" );
      xlsFile.getTableRowList().add( tableRow );
    }
    
    //warm up server
    for ( int ii = 0; ii < 10; ii++ )
    {
      this.resourceFactory.newResourceArbitraryObjectGraph().getArbitraryObjectGraph( 1 );
    }
    
    //
    for ( Integer graphDepth : graphDepths )
    {
      //
      double durationPerRequestInMillisecondsJSON = 0.0;
      double durationPerRequestInMillisecondsXML = 0.0;
      
      //
      for ( MediaType acceptType : Arrays.asList( MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_JSON_TYPE ) )
      {
        //
        boolean hasError = false;
        
        //
        ResourceArbitraryObjectGraph resourceArbitraryObjectGraph = this.resourceFactory.newResourceArbitraryObjectGraph();
        
        //
        WebClient.client( resourceArbitraryObjectGraph ).reset();
        WebClient.client( resourceArbitraryObjectGraph ).accept( acceptType );
        
        //
        DurationCapture durationCapture = DurationCapture.newInstance().startTimeMeasurement();
        int counter = 0;
        while ( durationCapture.getInterimTimeInMilliseconds() < cycleDuration )
        {
          try
          {
            resourceArbitraryObjectGraph.getArbitraryObjectGraph( graphDepth );
          }
          catch ( Exception e )
          {
            hasError = true;
          }
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
        System.out.println( "acceptType: " + acceptType );
        System.out.println( "graphDept: " + graphDepth );
        System.out.println( "request counter: " + counter );
        System.out.println( "whole duration: " + durationInMilliseconds + " [ms]" );
        System.out.println( String.format( "time for single request: %2.2f [ms]", durationPerRequestInMilliseconds ) );
        System.out.println( "error: " + hasError );
        
      }
      
      //
      TableRow tableRow = new TableRow( "" + graphDepth, String.format( "%2.2f", durationPerRequestInMillisecondsXML ),
                                        String.format( "%2.2f", durationPerRequestInMillisecondsJSON ) );
      xlsFile.getTableRowList().add( tableRow );
    }
    
    //
    xlsFile.store();
  }
  
  public void setResourceFactory( ResourceFactory resourceFactory )
  {
    this.resourceFactory = resourceFactory;
  }
  
}
