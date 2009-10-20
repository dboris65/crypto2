To create database structure, edit hibernate.cfg.xml file, line:
	<property name="hbm2ddl.auto">update</property>
	to
	<property name="hbm2ddl.auto">create</property>
After initial run, set hbm2ddl.auto to update.

 BeanShell installation:
 Drop bsh-2.0b4.jar (from project lib folder) into "C:\Program Files\Java\jdk1.x.x.x.x\jre\lib\ext" folder
 NOTE: If you have another jre folder, for example "C:\Program Files\Java\jre6", 
       BeanShell will (probably) not work correctly.
 
 