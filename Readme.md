# kerberos-demo
Spring Boot demonstration of Spring Security Kerberos for SSO

This example will show you how to:
 - Build SSO using Kerberos in an Windows environment
 - This application is tested running on a Windows 7 desktop
 - AD is Windows Server 2008 R2
 - Clients are MSIE11 and Chrome both running on Window 7 desktop
 - Java version is 1.8.131
 - Spring Security with full JAVA Annotations, no XML conf involved
 - Running with embedded Tomcat in version 8.5.15

Read the source code to learn about the various configurations 
required to make the application run.

# Generate a correct working keytab file
There is 2 small utillity classes available, that can
help verify a working setup.
 - JceTest - verifies full crypto support
 - KrbTest - verifies if your keytab file is usable

It cannot be stated enough, that most Kerberos related errors relates to the fact, that
the keytab file is not generated correctly.

Follow the guide here:
- https://tomcat.apache.org/tomcat-8.5-doc/windows-auth-howto.html

Test the keytab using the KrbTest class after customization to your setup. When the KrbTest can
successfully perform the following steps you are good to go:
  - Pre-Authenticate as the principal stored in the keytab (require JCE)
  - Extract the Kerberos Service Ticket

# Verify your progress using the provided full debug log
As there is so many steps required for a successfull login to happen I have provided a debug log.
  - See: DebugLog.md

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

# Credit to Karthikeyan Vaithilingam

Karthikeyan Vaithilingam wrote an excellent blog about how to configure kerberos for localhost
access - The code in this project is almost identical, with minor changes to adapt to Spring Security Java configuration
instead of the old XML based configuration.

# Usefull ressources

- http://docs.spring.io/spring-security-kerberos/docs/current/reference/htmlsingle/
- https://tomcat.apache.org/tomcat-8.5-doc/windows-auth-howto.html
- https://seenukarthi.com/security/2014/08/13/localhost-authentication-spring-kerberos/
