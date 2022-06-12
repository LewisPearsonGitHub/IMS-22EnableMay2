Coverage: 72.8%
# Inventory Management System Project - Lewis Pearson

This is the week 5 QA Academy Project to design an application that handles an Inventory Management System.

Users have full CRUD functionality and thus can Create, Read, Update, and Delete records from tables in the database. The tables included are Customers, Items, Orders, and Orders-Items. The customers table allows customer records to be created, items allows item records to be created, and orders allows order records to be created. Orders-Items allows multiple items to be added to an order.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The application was developed using Java, so to run it an install of Java will be required.

[Java 8 can be installed here](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html ) 
[Java 14 can be installed here ](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html)
More up-to-date versions may be available.

Double clicking the executable file that has been downloaded will prompt the install window, work through that to install it. Once installed the environment variable must be set up. Navigate to 'Edit the system environment variable' in your computer Control Panel settings, select environment variables and edit the System Variables so that there is a JAVA_HOME variable with path set to where java has been installed. Then right click on path and make sure the value is set to the java bin folder.

MySQL Workbench should be downloaded too, with system variable added like above.

Required dependencies have been including in the pom.xml file.


### Installing

Download the project folder from GitHub onto your local machine. This can be done by cloning the repository on GitHub by going to the main repository page, clicking the green button Code, copying the url, and using Git on your local machine run the command: 
`git clone [url]` 

and it will download.

To run the application, open a command line in the project folder and type:
`java -jar ims-0.0.1-jar-with-dependencies.jar`

![image](https://user-images.githubusercontent.com/105277446/173203540-8f135ec3-a0ab-441e-a7dc-2e8148ab2021.png)


A database that matches the table and column names used in the application will be required, and can be generated by running the Schema  included in the project folder in MySQL workbench (the schema in main, not the test schema).
Here is a demo of the application being used. First CUSTOMER is selected, and then the CRUD function READ and the application displays the only record in the table Customer with a customer ID of 5, firstname Lewis and surname Pearson.

![image](https://user-images.githubusercontent.com/105277446/173203585-4d64ce8e-0ee1-4b4f-8ffc-ac13149d5e63.png)

To ensure a fresh database, run the Schema file without running the SQL data file.

## Database

### Entity Relationship Diagrams

![Initial ERD](https://user-images.githubusercontent.com/105277446/173203716-4a4f2904-95b5-4cf6-a7fc-4e5e317254ee.png)

An initial ERD was produced based upon the project specification, however this did not allow multiple items to be assigned to a single order as both items_id and orders_id are primary keys. Therefore this was developed into:

![Second ERD](https://user-images.githubusercontent.com/105277446/173203786-64d8d93e-baca-4802-94c4-cacecaf4e0ff.png)

An intermediary table orders_items was created to allow this functionality and fulfill the project specification.

![Third ERD with quantity](https://user-images.githubusercontent.com/105277446/173203796-8a82e81f-8b32-4fe4-a13f-ddfa34978e74.png)

A third diagram was created after it was realised that another row in one of the tables was required - quantity. This allows a total cost per order to be properly calculated.

## Running the tests

Testing is done through using JUnit and Mockito. The tests can be run within Eclipse, by right clicking on the src/test/java folder in the Project Explorer (usually on the left hand side, but if not can be opened through Window->Show View-> Project Explorer) and click Run As -> JUnit test. Alternatively, instead of Run As click Coverage As -> JUnit test to highlight what has been covered by tests.

Ideally, test coverage would be above 80%, however in this case a coverage of only 72.8% was achieved. This is sufficient for the purpose of this project, and with more time, or more training on testing, coverage of >80% would be easily achieved.

Current tests cover most of the CRUD functionality for the various tables through testing the domain, DAO and controller files, and also testing most of the exceptions.

Unit testing was done using both JUnit and Mockito. JUnit tests were conducted by comparing an expected output with the actual output, and Mockito was used to mock data, simulate the application with simulated data.

Example of previous testing coverage.
![image](https://user-images.githubusercontent.com/105277446/173204027-b6120958-4115-4216-80d1-0a7f702d0017.png)



## Usage

Using the application is fairly straightforward, and it can be successfully navigated using the menu prompts. However, some things should be kept in mind. A customer must be created before an order can be created with a customer assigned to it. Additonally, a customer, item, and order must be created before a record in orders_items can be created. 

The total cost of an order will be displayed as null or 0 until items are assigned to the order.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Continuous Integration

### Versioning

[Git with GitHub](http://GitHub.com/) was used for version control. The main/dev/feature branch model was used with regular commits to keep track of progress and preserve work in case of data loss.

![image](https://user-images.githubusercontent.com/105277446/173203942-2bbc11ef-0a5b-4c5c-83d5-76b434d92d71.png)
![image](https://user-images.githubusercontent.com/105277446/173203947-cb7c0ef0-6315-4a80-a7d4-7d25a0e80ec5.png)

### Project Management

The project was managed through using a Scrum Board on Jira. User stories were created with tasks and story points assigned.

![image](https://user-images.githubusercontent.com/105277446/173203997-5f654915-558d-4813-9825-380ab4ae0a67.png)
![image](https://user-images.githubusercontent.com/105277446/173203999-08a33fcd-1f63-402d-80d3-5593325c5daa.png)


## Authors

* **Jordan Benbelaid** - *Initial work* - [jordanbenbelaid](https://github.com/jordanbenbelaid)
* **Lewis Pearson** - *Final project* - [lewispearson](https://github.com/LewisPearsonGitHub)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 



## Acknowledgments

* Thanks to the trainers Cameron Guthrie and Jordan Benbelaid for brilliant teaching
that gave me the knowledge to complete this project.
* And thanks to fellow colleagues in the QA Academy for any helps and support throughout the project week.
