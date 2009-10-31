
 Download and install HSQLDB from http://hsqldb.org/.
 Download and install Maven and Jetty. 

   
 1. Go to HSQLDB folder (for example: cd D:\FON\SavremeneSoftverskeArhitekture\JavaEclipse\hsqldb) 
    and type:
    java -cp ./lib/hsqldb.jar org.hsqldb.Server
    
 2. Install/Update Maven dependencies fromm Eclipse and run Tomcat or
    Go to crypto2 folder and type: 
    mvn jetty:run
    
 3. To create database structure, edit hibernate.cfg.xml file, line: 
 	<property name="hbm2ddl.auto">update</property>
	to
	<property name="hbm2ddl.auto">create</property>
 
 4. After initial run, set hbm2ddl.auto to update.


 5. BeanShell installation:
    Windows users: If you have two jre folders, for example "C:\Program Files\Java\jre6",
    and "C:\Program Files\Java\jdk1.6.0_12\jre" BeanShell (and many other things) will not work correctly.
 
 6. In Eclipse, open Project-->Properties-->Java compiler-->Errors/Warnings-->
    On some Eclipse instalations, you have to locate the "Forbidden reference (access rules)" 
    option under "Deprecated and restricted API"
    section in the dialog box. If it is set to Error, change it to Warning.  
    
 7. NOTE: Some of IE plugin's (YouTube video downloaders, Flashget)
    are in collision with Tapestry 5 Zone component.
    