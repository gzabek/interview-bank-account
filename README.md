# interview-bank-account

The example of Spring Boot application with REST service to get customer's balance for either all
accounts or given account. It uses in memory DB hsqldb and has one predefined customer with two
accounts.

There is a swagger REST documentation http://localhost:8080/gzabek/swagger-ui.html

 Default data
 

 GET http://localhost:8080/gzabek/bank/customer/1/balance
 
 GET http://localhost:8080/gzabek/bank/customer/1/balance/account/1
 
 GET http://localhost:8080/gzabek/bank/customer/1/balance/account/2
 
 
##### To launch application run jar
 
 `java -jar interview-bank-account-0.0.1-SNAPSHOT.jar`