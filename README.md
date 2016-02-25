This README file explains how to use our asset-centric, security-aware Cloud storage selection framework. The program was developed based on Java, Version 1.8.0_71.



---- RUN IN AN IDE ----

If you want to run our system in an IDE, such as Eclipse, you should copy the entire contents of our two folders namely: AssetAwareSecurity and AssetAwareSecurityServer into two distinct projects in the IDE.  Then two classes need to be executed in this particular order: Auctioneer.java in AssetAwareSecurityServer project and AssetAwareSecurity.java in AssetAwareSecuriry project.


---- COMPILE AND RUN ON THE COMMAND LINE ----

To compile our work from the commandline a user needs to first change directory to our AssetAwareSecuriryServer project (C:\..\AssetAwareSecurityServer\src\assetawaresecurity) and use the command javac *.java to compile our work. Given that the machine hosting our code supports Java 8 or higher, there should be no errors. Then use "java Auctioneer" to run our server/auctioneer. Once finished change directory to C:\..\AssetsAwareSecurity\src\assetawaresecurity. Then use the command javac *.java  to compile all .java files and use the command "java AssetAwareSecurity" to run our client.

                  
---- RUN EXECUTABLE JAR FILES -----     

There are two ways to run our jar files either by double-clicking on our AssetAwareSecurityServer.jar and AssetAwareSecurity.jar, in that order, or by opening a terminal and using the following commands:
java -jar AssetAwareSecurityServer
java -jar AssetAwareSecurity

----  USAGE ----

The Auctioneer.java class operates as our server/auctioneer, where the AssetAwareSecurity.java stands as our agent/client. For these two classes to communicate the user has to configure the IP address depending on the current setup. The preloaded port and IP address for both classes is 1234 and localhost respectively. In case that the Auctioneer.java class is operating in a seperate machine then the user needs to change the IP addres in file serverIP.txt accordingly. The serverIP.txt file can be found in C:\.......\AssetsAwareSecurity\Network\.
To use the AssetAwareSecurity.java client the user first needs to click on the Monitor Users tab in GUI and register with some of the available Cloud storage providers (with the respective storage accounts maintained by each user). Once registered, a user must click on the Security Policy Panel and either select and submit a premade security policy or create a new custom security policy. Finally, to select services for an asset, the user needs to copy a file into MainUserStorage folder in AssetAwareSecuriry project, where our algorithm will return the best available storage according to a user's security requirements. The results can be seen in the Assets tab in our client GUI.


