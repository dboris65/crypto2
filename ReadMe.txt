
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
    Drop bsh-2.0b4.jar (from project lib folder) into "C:\Program Files\Java\jdk1.x.x.x.x\jre\lib\ext" folder
    NOTE: If you have another jre folder, for example "C:\Program Files\Java\jre6", 
       BeanShell will (probably) not work correctly.
 
 6. In Eclipse, open Project-->Properties-->Java compiler-->Errors/Warnings-->
    Locate the "Forbidden reference (access rules)" option under "Deprecated and restricted API"
    section in the dialog box. If it is set to error, change it to Warning. This will allow BeanShell
    to run properly.