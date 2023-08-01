# patient-transfer
A small JEE server maven project which has a transfer functionality to read out a patient with an id from a fhir server and creates a copy of the first name entry (name is an array in the JSON Object): family, given, prefix and suffix, together with gender and birthdate in a sql table.


To build and run - execute these commands in /patient-transfer
1. mvn clean package
2. cd docker (Navigate to /docker folder)
3. docker build -t wildfly-image .
4. docker-compose up
