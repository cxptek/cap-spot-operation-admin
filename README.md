## Cex-Admin-Backend

## Prerequisite
For building and running the application you need:
- JDK 21 or later
- Maven
- IntelliJ

## Setup
### Setup variables environment
- MacOS
    - Open terminal and execute statement:
      ```shell
      echo export "JAVA_HOME=\$(/usr/libexec/java_home)" >> ~/.zshenv
      ```
    - Force to close the terminal by pressing `command + Q` then open the terminal again and check the result with a command:
      ```shell
      echo $JAVA_HOME
      ```
- Windows
    - Open Command Prompt (make sure you Run as administrator so you're able to add a system environment variable).
    - Set the value of the environment variable to your JDK installation path as follows:
      ```shell
      setx /m JAVA_HOME "C:\Program Files\Java\{your jdk path}"
      ```
    - Restart Command Prompt to reload the environment variables then use the following command to check the it's been added correctly.
      ```shell
      echo %JAVA_HOME%
      ```
      You should see the path to your JDK installation.
## Build and run the application locally

### Build project
  ```shell
    mvn clean install
  ```
### Build project skip Tests
  ```shell
    mvn clean install -Dmaven.test.skip=true
  ```

### How to build and run docker image
- Run docker image

### Run Spring Boot Application
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the {module}Application class from your IDE.

{module}: It's the prefix name of the project that you would like to run. For example, I would to run cex-backend project, the application name would be `CexBackendApplication`

Or go to your child project and run the following command in a terminal:
```shell
mvn spring-boot:run
```

### Health check app running
```shell
http://localhost:8080/actuator/health
```

### API documentation
```shell
http://localhost:8080/swagger-ui/index.html
```

## Flyway Database migration
Flyway adheres to the following naming convention for migration scripts:

<Prefix><Version>__<Description>.sql

Where:

- `<Prefix>` – Default prefix is V, which may be configured in the above configuration file using the flyway.sqlMigrationPrefix property.
- `<Version>` – Migration version number. Major and minor versions may be separated by an underscore. The migration version should always start with 1.
- `<Description>` – Textual description of the migration. The description needs to be separated from the version numbers with a double underscore.
  Example: `V1_1_0__my_first_migration.sql`

So, let's create a directory db/migration in $PROJECT_ROOT with a migration script.

## Run Testing

### Run Unit test
```shell
mvn test
```
