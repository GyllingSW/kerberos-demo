# kerberos-demo
Spring Boot demonstration of Spring Security Kerberos for SSO

This example will show you how to:
 - Build SSO using Kerberos in a Windows environment
 - This application is tested running on a Windows 7 desktop
 - AD is Windows Server 2008 R2
 - Clients are MSIE11 and Chrome both running on Window 7 desktop
 - Java version is 1.8.131
 - Spring Security with full JAVA Annotations, no XML conf involved
 - Running with embedded Tomcat in version 8.5.15

Read the source code to learn about the various configurations 
required to make the application run. Be warned - It is very complicated!

# Generate a correct working keytab file
There is 2 small utility classes available, that can
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
As there is so many steps required for a successful login to happen I have provided a debug log.
  - See: DebugLog.md

# Java Cryptography Extension (JCE)
To get up and running, the JRE/JDK running this application must
be enhanced with the US export restricted crypto package.

Grab your copy here:
- http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html

Note, that Open JDK versions includes the required crypto support.

# Credit to Karthikeyan Vaithilingam

Karthikeyan Vaithilingam wrote an excellent blog about how to configure kerberos for localhost
access - The code in this project is almost identical, with minor changes to adapt to Spring Security Java configuration
instead of the old XML based configuration.

# Useful resources

- http://docs.spring.io/spring-security-kerberos/docs/current/reference/htmlsingle/
- https://tomcat.apache.org/tomcat-8.5-doc/windows-auth-howto.html
- https://seenukarthi.com/security/2014/08/13/localhost-authentication-spring-kerberos/
