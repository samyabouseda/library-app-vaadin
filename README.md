# A simple library management app
Simple Library is a very simple app built with [Vaadin](https://vaadin.com/) to apply and test our knowledge of the framework.

### Prerequisites

1. Download the [TomEE WebProfile](https://tomee.apache.org/download-ng.html) v7.0.x.
2. Download the WAR of the app [here](https://app.box.com/s/47o6s4ss3huw1ixzud74r9x5s6o988mt).

### Installing

Unzip the downloaded TomEE file into a directory. TomEE will be unzipped into a directory called something similar to

```
apache-tomee-webprofile-7.0.x.
```

### Adding new user
Tomcat configuration files are found in the directory: CATALINA_HOME/conf (where CATALINA_HOME environment variable is the TomEE installation directory). In the $CATALINA_HOME/conf directory, locate the `tomcat-ursers.xml` file and open it with a text editor.

In order to add new users, you need to add the following lines to the file.
```
<role rolename="ADMIN" />
<user username="admin" password="<must-be-changed>" roles="ADMIN" />
```
Be careful to add these lines in between of the tags
```
<tomcat-users></tomcat-users>
```

Here is an example of such a tomcat-users.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<tomcat-users xmlns="http://tomcat.apache.org/xml"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"
              version="1.0">
              
    <role rolename="ADMIN" />
    <user username="highlander" password="ramirez" roles="ADMIN" />
</tomcat-users>
```

You would then use "highlander" as username and "ramirez" as password when login to the app.

### Deployment
Web applications are deployed under the $CATALINA_HOME/webapps directory. One simple way to deploy the app is by dropping the WAR file (downloaded previously) into the $CATALINA_HOME/webapps directory of the Tomcat instance.

Once it's done, you can start the Tomcat server by simply running the startup script located in the $CATALINA_HOME/bin directory. There is a `startup.bat` and a `startup.sh` in every installation.

Choose the appropriate option depending on whether you are using a Windows(.bat) or Unix(.sh) based operating system.

### Setting JAVA_HOME
It's possible that you run into an error if the JAVA_HOME environment variable is not set.
To fix this, follow the steps [here](https://confluence.atlassian.com/crowd/setting-java_home-61604243.html).

### Using the app
Finnaly to access the app, open your favorite web browser and go to:

[http://localhost:8080/library](http://localhost:8080/library)

## Authors

* **Samy Abouseda** - [samyabouseda](https://github.com/samyabouseda)
