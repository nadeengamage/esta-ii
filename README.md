# Assignment II - UVT

This is an example project about RESTful API services.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Requirements

    - Angular 8
    - Java 1.8 or letter
    - Maven 3

### Prerequisites

Download the project using the below URL.

```sh
$ https://github.com/nadeengamage/assignment-ii-esta.git
```

### Installation

Now change current directory to /assignment-ii-esta/frontend. And install the node modules using the below-mentioned code.

```sh
$ npm install
```

Then start the frontend application.

```sh
$ ng serve
```

Now change the current directory to /assignment-ii-esta/backend and change the database connection details on **application.properties** file which is located in the resources directory. 

Create a WAR file.

```sh
$ mvn clean package
```

Then change the current directory to /target and put below code in your terminal.

```sh
$ java -jar assignment-1.0.0.jar
```


License
----

Apache 2.0






