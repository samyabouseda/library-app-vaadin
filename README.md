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
In order to add new users, you need to add the following lines to tomcat-users.xml.
```
<role rolename="ADMIN" />
<user username="admin" password="<must-be-changed>" roles="ADMIN" />
```
Be carfule to add these lines in between of the tags
```
<tomcat-users></tomcat-users>
```

Here is an example of a tomcat-users.xml
```
<tomcat-users xmlns="http://tomcat.apache.org/xml"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"
              version="1.0">
              
    <role rolename="ADMIN" />
    <user username="highlander" password="ramirez" roles="ADMIN" />
</tomcat-users>
```

You would then user "highlander" as username and "ramirez" as password while login to the app.
