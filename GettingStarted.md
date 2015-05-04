# Setup of a build environment #

## Prerequisites ##

  * Java 1.6+
  * Apache Maven 3.0.3+
  * Tomcat 7+


## Maven dependencies ##

  * [Apache CXF](http://cxf.apache.org/)
  * [Jersey](http://jersey.java.net/)
  * JSR-311
  * [utils-apl-derived](http://code.google.com/p/utils-apl-derived/)(other project)

  * [Doclet to generate documentation from Javadoc by www.lunatech-labs.com](http://www.lunatech-labs.com/open-source/jax-doclets)


## Checkout source from SVN ##

Checkout source from SVN for this projects and the **utils-apl-derived** project.

Import the projects into Eclipse via the **Import existing Maven projects..." wizard**

## Dependency: utils-apl-derived ##

The utils-apl-derived project is basically a library containing utility classes. It is not available in Maven Repo1 yet, but within the SNAPSHOT repository from Sonatype

https://oss.sonatype.org/content/repositories/snapshots/

The Maven dependency is
```
<dependency>
  <groupId>org.omnaest.utils</groupId>
  <artifactId>utils-apl-derived</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

This utils project offers some of the JAXB delegate classes to wrap arbitrary maps and lists, as well as a possibility to log data directly into Microsoft Excel xls files.

# Project overview #

![http://svn.codespot.com/a/eclipselabs.org/restful-webservice-demo-project/wiki/images/subprojects_overview.png](http://svn.codespot.com/a/eclipselabs.org/restful-webservice-demo-project/wiki/images/subprojects_overview.png)

The projects are grouped into a Apache Maven nested module project.
The root project is called:
  * webservice-evaluation
and it includes following nested module projects:
  * client-apache-cxf
  * client-jersey
  * webapp-apache-cxf
  * webapp-jersey

The client projects are representing REST clients. They include JUnit test cases which simulates a client by requesting data from the web application projects.

![http://svn.codespot.com/a/eclipselabs.org/restful-webservice-demo-project/wiki/images/apache_CXF_client_JUnit_tests.png](http://svn.codespot.com/a/eclipselabs.org/restful-webservice-demo-project/wiki/images/apache_CXF_client_JUnit_tests.png)

The webapp projects are deployable web applications which have to be deployed into a tomcat to allow the client projects JUnit tests to be runnable.

The result of the JUnit tests are logged directly into an Microsoft Excel xls file which can be found within the root of the client project:

![http://svn.codespot.com/a/eclipselabs.org/restful-webservice-demo-project/wiki/images/apache_CXF_client_performance_result.png](http://svn.codespot.com/a/eclipselabs.org/restful-webservice-demo-project/wiki/images/apache_CXF_client_performance_result.png)

## Deploy webapps into an Eclipse integrated Tomcat ##

![http://svn.codespot.com/a/eclipselabs.org/restful-webservice-demo-project/wiki/images/eclipse_tomcat_conf.png](http://svn.codespot.com/a/eclipselabs.org/restful-webservice-demo-project/wiki/images/eclipse_tomcat_conf.png)

## Configuration of the clients ##

The Apache CXF client factory has to be configured with a valid url address pointing towards the running web application projects.

This can be achieved in the following file:

![http://svn.codespot.com/a/eclipselabs.org/restful-webservice-demo-project/wiki/images/apache_CXF_client_configuration.png](http://svn.codespot.com/a/eclipselabs.org/restful-webservice-demo-project/wiki/images/apache_CXF_client_configuration.png)

The ports there have to match your Apache Tomcat ports.

## Resource module ##

The REST resources are separated into a single project

  * webservice-resources

which is referenced by the client as well as the web application projects.
Its the single point of truth for them as well for the documentation of the REST services produced by the lunatech-doclet which is included as Apache Maven plugin within the resource projects POM.xml.

The resources within the project are declared using interfaces. The interfaces are used by the client and the webapps, where the implementations are only used by the webapps, the clients will create proxies based on the interfaces only.

![http://svn.codespot.com/a/eclipselabs.org/restful-webservice-demo-project/wiki/images/Resources_project.png](http://svn.codespot.com/a/eclipselabs.org/restful-webservice-demo-project/wiki/images/Resources_project.png)
