# kerberos-demo
Spring Boot demonstration of Spring Security Kerberos for SSO

This example will show you how to:
 - Build SSO using Kerberos in an Windows environment
 - This application is tested running on Windows7
 - AD is Windows Server 2008 R2
 - Java version is 1.8.131
 - Spring Security with full JAVA Annotations, no XML conf involved
 - Running with embedded Tomcat in version 8.5

Read the source code to learn about the various configurations 
required to make the application run.

There is 2 small utillity classes available, that can
help verify
 - JceTest - verifies full crypto support
 - KrbTest - verifies if your keytab file is usable

# Usefull ressources

- https://tomcat.apache.org/tomcat-8.5-doc/windows-auth-howto.html

