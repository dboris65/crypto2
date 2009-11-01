
 Download and install Maven. 

    
 1. Install/Update Maven dependencies from Eclipse and run Tomcat or
    Go to crypto2 folder and type: 
    mvn jetty:run
    
 2. To create database structure, edit hibernate.cfg.xml file, line: 
 	<property name="hbm2ddl.auto">update</property>
	to
	<property name="hbm2ddl.auto">create</property>
 
 3. After initial run, set hbm2ddl.auto to update.
 
 4. On some Eclipse installations, you need to applay following settings in order to start BeanShell:
    In Eclipse, open Project-->Properties-->Java compiler-->Errors/Warnings-->
    On some Eclipse instalations, you have to locate the "Forbidden reference (access rules)" 
    option under "Deprecated and restricted API"
    section in the dialog box. If it is set to Error, change it to Warning.  
    
 5. NOTE: Some of IE plugin's (YouTube video downloaders, Flashget)
    are in collision with Tapestry 5 Zone component.
    
 8. To configure Firefox for Selenium tests, use following instructions:
 http://girliemangalo.wordpress.com/2009/02/05/creating-firefox-profile-for-your-selenium-rc-tests/
 http://seleniumhq.org/docs/05_selenium_rc.html#specifying-the-firefox-profile
    
    
Not needed anymore (Solved):   
/*********************************************************************************************/
Go to HSQLDB folder (for example: cd D:\FON\SavremeneSoftverskeArhitekture\JavaEclipse\hsqldb) 
    and type: java -cp ./lib/hsqldb.jar org.hsqldb.Server   
/*********************************************************************************************/
BeanShell installation:
    Windows users: If you have two jre folders, for example "C:\Program Files\Java\jre6",
    and "C:\Program Files\Java\jdk1.6.0_12\jre" BeanShell (and many other things) will not work correctly.
/*********************************************************************************************/
    
