
 Download and install Maven. 

    
 1. Go to crypto2 folder and type:
    mvn compile 
    mvn jetty:run (and in your browser type http://localhost:8080/crypto2/)
    mvn test
    mvn integration-test
    

 2. Selenium integration tests are configured to run on windows using SeleniumBrowser property,
    with *firefox value in pom.xml file.
    Under windows, I tried it with 
      *iehta
      *iexplore
      *googlechrome
      *chrome
      *firefox
      *firefox3
      *opera
    Under Linux (Fedora 11), I tried it with
      *firefox
      *chrome  
    
    If you are trying it with different browser on 
    different OS, do not forget to set up PATH and file permissions.
       
 3. On some Eclipse installations, you need to apply following settings in order to start BeanShell:
    In Eclipse, open Project-->Properties-->Java compiler-->Errors/Warnings-->
    On some Eclipse installations, you have to locate the "Forbidden reference (access rules)" 
    option under "Deprecated and restricted API"
    section in the dialog box. If it is set to Error, change it to Warning.  
    
 4. NOTE: Some of IE plugin's (YouTube video downloaders, Flashget)
    are in collision with Tapestry 5 Zone component.
    