
 Download and install Maven. 

    
 1. Install/Update Maven dependencies from Eclipse and go to crypto2 folder and type: 
    mvn jetty:run
    or run Tomcat

 2. Selenium integration tests are configured to run on windows using 
    *iehta property in pom.xml file. If you are trying it with different browser on 
    different OS, do not forget to set up PATH and file permissions.
       
 3. On some Eclipse installations, you need to apply following settings in order to start BeanShell:
    In Eclipse, open Project-->Properties-->Java compiler-->Errors/Warnings-->
    On some Eclipse installations, you have to locate the "Forbidden reference (access rules)" 
    option under "Deprecated and restricted API"
    section in the dialog box. If it is set to Error, change it to Warning.  
    
 4. NOTE: Some of IE plugin's (YouTube video downloaders, Flashget)
    are in collision with Tapestry 5 Zone component.
    