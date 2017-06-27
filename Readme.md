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

# Java Cryptography Extention (JCE)
To get up and running, the JRE/JDK running this application must
be enhanced with the US export restricted crypto package.

Grap your copy here:
- http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html

# Spring Boot bug
In version 1.4.3.RELEASE of the Spring Boot Maven plugin there is
introduced a packaging bug. To run this application as a fat jar
the version of the Spring Boot Maven plugin must be <= 1.4.2.RELEASE

 - See: https://github.com/spring-projects/spring-boot/issues/8324

If you encounter this bug, the Spring Security setup can't find the
login page, thus nothing works....

# Usefull ressources

- http://docs.spring.io/spring-security-kerberos/docs/current/reference/htmlsingle/
- https://tomcat.apache.org/tomcat-8.5-doc/windows-auth-howto.html
- https://seenukarthi.com/security/2014/08/13/localhost-authentication-spring-kerberos/
