[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)
# Create an application using J2ee standard without using any framework

This project model a simple application managing courses (get all and create a course)
A course entity has 

## Prerequisites

* Java 8+ and maven 3 installed.

## How to run application

Using maven you can create package with command:
```
$ mvn package
```

If command ends successfully inside target/bin you have a script to run your application

```
$ ./autentia (unix/linux)
autentia.bat (windows)
```

Access to you application with your favorite browser

```
localhost:8080/course (get all)

localhost:8080/course (post)
```