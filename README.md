## Invitation microservice
The Invitation Micro service reads in customer records from a text file (customers.txt) and produces an output file (output.txt). 
The output file contains a list of names and userIds of customers who are within a given distance (100Km) from a given location (Intercom's Dublin Office).


The given distance, and latitude and longitude of the given location are stored in the application.properties file, as seen below.
````
dublin.office.location.longitude=-6.257664
dublin.office.location.latitude=53.339428
dublin.office.invitation.range=100
````
This values can be altered before running the application locally if desired. 

In order to run this locally we need to provide a customer records txt file in the following folder src/main/resources/
The file name is currently set to customer.txt in the application.properties. see below. 
```
customer.records.file.name=customers.txt
````

##Local Deployments

####Execute the following commands to run the application locally
mvn clean install, will compile and package the application, creating a jar file in the /targets folder, which we can run.
```shell script
mvn clean install
```

The newly create jar file can be ran with the following command.  
```shell script
java -jar target/takehome-0.0.1-SNAPSHOT.jar
```
This will run the application, reading in customer records from a text file customers.txt found at src/main/resources/ and creating an output.txt file.

Alternatively we can pass in a command line arg "s3" retrieve the file from the s3 bucket specified in the application.properties. This too will create an output.txt file.
```shell script
java -jar target/takehome-0.0.1-SNAPSHOT.jar s3
```


