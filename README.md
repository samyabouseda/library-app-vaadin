# A simple library management app
Simple Library is a very simple app built with [Vaadin](https://vaadin.com/) to apply and test my knowledge of the framework.

## Deployment

1. Download the [TomEE WebProfile](https://tomee.apache.org/download-ng.html) v7.0.x.
2. Download the WAR of the app [here](http://somelink.com).
3. Go inside of the Place the WAR inside of the webapps directory.
```
$CATALINA_HOME\webapps 
```
Web applications are deployed under the $CATALINA_HOME\webapps directory.

4. Start the Tomcat server by simply running the startup script located at $CATALINA_HOME\bin\startup. There is a .bat and a .sh in every installation.

```
$CATALINA_HOME/bin/startup.sh
```
Choose the appropriate option depending on whether you are using a Windows or Unix based operating system.
### Adding new user
Then locat the `tomcat-ursers.xml` file which should be   $CATALINA_HOME/bin/startup.sh
In order to add new users, you need to add the following lines to tomcat-users.xml.
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

You would then user "highlander" as username and "ramirez" as password while login to the app.

### Accessing the app
In order to access the app, open your favorite browser and go to:

[http://localhost:8080/simple_library_war_exploded/](http://localhost:8080/simple_library_war_exploded/)

## Authors

* **Samy Abouseda** - [samyabouseda](https://github.com/samyabouseda)
* **Thomas Carreira** - [ThomasCarreira](https://github.com/ThomasCarreira)
