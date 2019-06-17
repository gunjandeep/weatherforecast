## Weather Forecast Data Demo Application

The following execution is inside the Shell terminal.

## Set up the development environment
Assume the Maven home and JDK in your local environment are /DevTools/apache-maven-3.3.9
and /DevTools/Java/jdk1.8.0_144

* Set up execution path to both Maven and JKD binary directories
    * export PATH={maven_directory}/bin:{jdk_bin_directory}/bin:$PATH 
      where {maven_directory} is your local Maven directory  
      and {jdk_directory} is your local JDK directory

* Set up Maven_HOME
    * export MAVEN_HOME={maven_directory}

* Set up JAVA_HOME
    * export JAVA_HOME={jdk_directory}
    
## Maven run method

* Change directory to the project directory openweather
* Run tests
    * mvn test
* Clean and build
    * mvn clean install
* Start Spring Boot application
    * mvn spring-boot:run
* Run browser and open home page
    * http://localhost:8080/weather-forecast-data-home-page
    
## List of Enhancements that need to be implemented

* Display temperature with 2 decimal places

* After invoking Open Weather Map API get method for a city,
use JSON Mapper instead of parser to convert JSON string to Java object

* For better security purpose, store sensitive information such as the appid into environment variables
instead of plain properties file.


* The API uses the country code which is currently being taken from the page instead of the JSON. So, it should be taken from the json . 

* The City should be taken from property file for the dropdown instead of hardcoding them.