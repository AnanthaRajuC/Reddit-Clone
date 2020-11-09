# Reddit Clone

[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FReddit-Clone.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FReddit-Clone?ref=badge_shield)

Backend of the clone of the popular social media platform [reddit](https://www.reddit.com/)

## Summary

  - [Technology Stack](#technology-stack)
  - [Getting Started](#getting-started)
  - [Explore the APIs](#explore-the-apis)
  - [Runing the tests](#running-the-tests)
  - [Deployment](#deployment)
  - [Contributing](#contributing)
  - [Reporting Issues and Suggesting Improvements](#reporting-issues-and-suggesting-improvements)
  - [Versioning](#versioning)
  - [Authors](#authors)
  - [License](#license)
  - [Acknowledgments](#acknowledgments)

## Technology Stack

### Data

* 	[Flyway](https://flywaydb.org/) - Version control for database
* 	[MySQL](https://www.mysql.com/) - Open-Source Relational Database Management System

### Server - Backend

* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Maven](https://maven.apache.org/) - Dependency Management

###  Libraries and Plugins

* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.

### Others 

* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system

### External Tools & Services

* 	[gitignore.io](https://www.toptal.com/developers/gitignore/api/java,eclipse,intellij) - Create useful .gitignore files for your project.
*	[Dependabot](https://dependabot.com/) - Automated dependency updates.
* 	[Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)
*	[Mailtrap](https://mailtrap.io/) - Safe Email Testing for Staging & Development.
*   [Contributor Covenant](https://www.contributor-covenant.org/) - Used for the Code of Conduct.

## Getting Started

These instructions will get you a copy of the project up and running on
your local machine for development and testing purposes. See deployment
for notes on how to deploy the project on a live system.

### Prerequisites

*	You need to have **MySQL** installed on your machine to run the application in **`dev`** profile. Using the `MySQL Workbench` or on any other MySQL client/console, create a database/schema named `reddit_clone`. 

~~~sql
-- create schema
CREATE SCHEMA reddit_clone;

-- use schema
USE reddit_clone;

-- Create user 
create user 'reddit_clone'@'localhost' identified by 'reddit_clone';

-- Grant privileges to user
grant all privileges on *.* to 'reddit_clone'@'localhost' with grant option;
~~~

After creating the database/schema, you need to add your **MySQL** `username` and `password` in the `application-dev.properties` file on `src/main/resource`. The lines that must be modified are as follows:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/reddit_clone?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=reddit_clone
spring.datasource.password=reddit_clone
```

*	A Java Keystore File is required to generate JSON Web Token.

```shell
keytool -genkey -alias redditclone -keyalg RSA -keystore redditclone.jks -keysize 2048
```

<img src="documents\reddit-clone-jks-generation.PNG"/>

### EER Diagram

<img src="documents\reddit-clone-eer-diagram.png"/>

### Installing

### Running the application with IDE

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `io.github.anantharajuc.rc.RedditCloneApplication` class from your IDE.

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
* 	Open Command Prompt and Change directory (cd) to folder containing pom.xml
* 	Open Eclipse
	* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Choose the Spring Boot Application file (search for @SpringBootApplication)
* 	Right Click on the file and Run as Java Application

### Running the application with Maven

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
$ git clone https://github.com/Spring-Boot-Framework/Reddit-Clone.git
$ cd Reddit-Clone
$ mvn spring-boot:run
```

### Running the application with Executable JAR

The code can also be built into a jar and then executed/run. Once the jar is built, run the jar by double clicking on it or by using the command 

```shell
$ git clone https://github.com/Spring-Boot-Framework/Reddit-Clone.git
$ cd Reddit-Clone
$ mvn package -DskipTests
$ java -jar target/Reddit-Clone-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

To shutdown the jar, follow the below mentioned steps on a Windows machine.

*	In command prompt execute the **jcmd** command to print a list of all running Java processes
*	**Taskkill /PID PROCESS_ID_OF_RUNNING_APP /F** execute this command by replacing the **PROCESS_ID_OF_RUNNING_APP** with the actual process id of the running jar found out from executing the previous command

## Explore the APIs

* 	Postman Collection for offline testing is available in the **documents** folder.

### Application URLs

|                                               URL                        | Method | Remarks | Sample Valid Request Body |
|--------------------------------------------------------------------------|--------|---------|---------------------------|
|`http://localhost:8080/api/auth/signup`                                   | POST   |         | [JSON](#signup)           |
|`http://localhost:8080/api/auth/accountVerification/{verification-token}` | GET    |         |                           |

### Sample Valid JSON Request Bodys

##### <a id="signup">Signup -> /api/auth/signup</a>
```json
{
    "username":"johndoe",
    "email":"domain@example.com",
    "password":"abcd1234"
}
```

## Running the tests

```shell
$ mvn test       //Run all the unit test classes.
```

### Break down into end to end tests

Explain what these tests test and why

    Give an example

### And coding style tests

Explain what these tests test and why

    Give an example

## Deployment

Add additional notes about how to deploy this on a live system

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Reporting Issues and Suggesting Improvements

This Project uses GitHub's integrated issue tracking system to record bugs and feature requests. If you want to raise an issue, please follow the recommendations below:

* 	Before you log a bug, please [search the issue tracker](https://github.com/Spring-Boot-Framework/Reddit-Clone/search?type=Issues) to see if someone has already reported the problem.
* 	If the issue doesn't already exist, [create a new issue](https://github.com/Spring-Boot-Framework/Reddit-Clone/issues/new)
* 	Please provide as much information as possible with the issue report.
* 	If you need to paste code, or include a stack trace use Markdown +++```+++ escapes before and after your text.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository(https://github.com/Spring-Boot-Framework/Reddit-Clone/tags)

## Authors

  - **Anantha Raju C** - [AnanthaRajuC](https://github.com/AnanthaRajuC)

See also the list of [contributors](https://github.com/Spring-Boot-Framework/Reddit-Clone/graphs/contributors) who participated in this project.

## License

- This project is licensed under the [MIT License](LICENSE.md) - see the [LICENSE.md](LICENSE.md) file for details

- FOSSA third-party code, license compliance and vulnerabilities

[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FReddit-Clone.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FReddit-Clone?ref=badge_large)

## Acknowledgments

  - https://github.com/SaiUpadhyayula/spring-reddit-clone is used as a starting point/reference for this project.
