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

import org.junit.Before;
import org.junit.Test;
import org.omnaest.utils.io.XLSFile;
import org.omnaest.utils.io.XLSFile.TableRow;
import org.omnaest.utils.time.DurationCapture;

import com.sun.jersey.api.client.WebResource;

/**
 * @author Omnaest
 */
public class ResourcePerformanceTest
{
  /* ********************************************** Constants ********************************************** */
  public final static String URL_BASE        = "http://localhost:8082/webapp-apache-cxf"; // "http://localhost:8082/webapp-jersey"; //"http://localhost:8082/webapp-jersey";
                                                                                          
  /* ********************************************** Beans / Services ********************************************** */
  
  protected ResourceFactory  resourceFactory = new ResourceFactory( URL_BASE );
  
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
    
    //
    WebResource webResource = this.resourceFactory.newWebResource( "ResourceArbitraryObjectGraph" );
    
    //
    webResource.accept( MediaType.APPLICATION_XML_TYPE );
    webResource.type( MediaType.APPLICATION_XML_TYPE );
    
    //
    int counter = 0;
    while ( durationCapture.getInterimTimeInMilliseconds() < 1000 )
    {
      //
      webResource.queryParam( "graphDept", "5" ).get( String.class );
      
      //
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
      //
      WebResource webResource = this.resourceFactory.newWebResource( "ResourceArbitraryObjectGraph" );
      webResource.accept( MediaType.APPLICATION_XML_TYPE );
      webResource.type( MediaType.APPLICATION_XML_TYPE );
      
      //
      webResource.queryParam( "graphDept", "5" ).get( String.class );
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
        WebResource webResource = this.resourceFactory.newWebResource( "ResourceArbitraryObjectGraph" );
        webResource.accept( acceptType );
        webResource.type( acceptType );
        
        //
        DurationCapture durationCapture = DurationCapture.newInstance().startTimeMeasurement();
        int counter = 0;
        while ( durationCapture.getInterimTimeInMilliseconds() < cycleDuration )
        {
          try
          {
            //
            webResource.queryParam( "graphDept", "" + graphDepth ).get( String.class );
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
